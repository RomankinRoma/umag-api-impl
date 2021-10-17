package kz.reself.impl.service.impl;

import static kz.reself.impl.constant.Constants.SUCESS_STATUS_CODE;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.reself.impl.feign.ProdCardItemClient;
import kz.reself.impl.feign.implClient;
import kz.reself.impl.model.CrmRequest;
import kz.reself.impl.model.CrmResponseDto;
import kz.reself.impl.model.ProdCardItem;
import kz.reself.impl.model.ProductStatus;
import kz.reself.impl.model.Production;
import kz.reself.impl.model.impl;
import kz.reself.impl.model.integration.Debit;
import kz.reself.impl.model.integration.DebitedProduct;
import kz.reself.impl.model.integration.Decomission;
import kz.reself.impl.model.integration.DecomissionedProduct;
import kz.reself.impl.model.integration.ProductBarcodeInf;
import kz.reself.impl.model.integration.ProductDetail;
import kz.reself.impl.repository.ProductionRepository;
import kz.reself.impl.service.IIntegrationService;
import kz.reself.impl.service.IProductionService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductionServiceStubImpl implements IProductionService {

  @Autowired
  private ProductionRepository productionRepository;
  @Autowired
  private implClient implClient;
  @Autowired
  private ProdCardItemClient prodCardItemClient;
  @Autowired
  private RabbitTemplate rabbitTemplate;
  @Autowired
  private IIntegrationService iIntegrationService;

  @Override
  public Production getById(Long id) {
    return productionRepository.findById(id).get();
  }

  @Override
  public List<Production> getAll() {
    return productionRepository.findAll();
  }

  @Override
  public void deleteById(Long id) {
    productionRepository.deleteById(id);
  }

  @Override
  public Production create(Production production) {
    return productionRepository.saveAndFlush(production);
  }

  @Override
  public void receiveResponse(CrmResponseDto s) {
    Production production = s.getProduction();
    if (s.getStatusCode() == SUCESS_STATUS_CODE) {
      production.setProductStatus(ProductStatus.SENT);
      productionRepository.saveAndFlush(production);
    } else {
      production.setProductStatus(ProductStatus.ERROR);
      productionRepository.saveAndFlush(production);
    }
  }

  @Override
  public impl createTemplate(impl impl) {
    List<ProdCardItem> prodCardItemList = impl.getProdCardItemList();
    impl.setProdCardItemList(null);
    ObjectMapper mapper = new ObjectMapper();
    impl = mapper.convertValue(implClient.create(impl).getBody(),
        impl.class);
    Long id = impl.getId();
    for (ProdCardItem prodCardItem : prodCardItemList) {
      prodCardItem.setMbId(impl.getId());
    }
    prodCardItemClient.saveAll(prodCardItemList);
    return impl;
  }


  @Override
  public CrmRequest sendToQueqe(Long id, String quantity) {
    ObjectMapper objectMapper = new ObjectMapper();
    impl impl =
        objectMapper.convertValue(implClient.getById(id).getBody(), impl.class);
    impl.setId(null);
    impl.getProdCardItemList().forEach(prodCardItem -> prodCardItem.setId(null));
    impl.setQuantity(impl.getQuantity() * Long.parseLong(quantity));
    impl = createTemplate(impl);
    impl =
        objectMapper.convertValue(implClient.getById(impl.getId()).getBody(),
            impl.class);
    List<ProdCardItem> prodCardItemList = impl.getProdCardItemList();
    Production production = Production.builder()
        .mbId(impl.getId())
        .productStatus(ProductStatus.DRAFT)
        .multiplier(Long.valueOf(quantity))
        .additionalCost(impl.getAdditionalCost())
        .storeId(3L)
        .build();

    Decomission decomission = new Decomission();
    Debit debit = new Debit();
    List<DecomissionedProduct> decomissionedProducts = new ArrayList<>();
    List<DebitedProduct> debitedProducts = new ArrayList<>();
    for (ProdCardItem prodCardItem : prodCardItemList) {

      ProductBarcodeInf productBarcodeInf =
          iIntegrationService.getProductById(prodCardItem.getBarcode(),
              String.valueOf(production.getStoreId()));
      ProductDetail productDetail =
          getProdDetailFromProdInfo(productBarcodeInf, production.getStoreId().toString());

      DecomissionedProduct decomissionedProduct =
          setDecProductDetails(productBarcodeInf, Long.valueOf(quantity));
      decomissionedProduct.setProductDetail(productDetail);

      DebitedProduct debitedProduct =
          setDebProductDetails(productBarcodeInf, Long.valueOf(quantity));
      debitedProduct.setProductDetail(productDetail);

      decomissionedProducts.add(decomissionedProduct);
      debitedProducts.add(debitedProduct);
    }
    decomission.setDecomissionedProducts(decomissionedProducts);
    debit.setDebitedProducts(debitedProducts);
    decomission.setTime(getCurrentDate());
    debit.setTime(getCurrentDate());
    decomission.setStockIn(true);
    debit.setStockIn(true);
    decomission.setType("0");
    debit.setType("0");
    decomission.setComment("Hello from Production");
    debit.setComment("Hello from Production");
    CrmRequest crmRequest = new CrmRequest();
    crmRequest.setDecomission(decomission);
    crmRequest.setDebit(debit);
    production = productionRepository.saveAndFlush(production);
    crmRequest.setProduction(production);
    rabbitTemplate.convertAndSend("decomission", "general", crmRequest);
    rabbitTemplate.convertAndSend("debit", "debit", crmRequest);
    return crmRequest;
  }

  private ProductDetail getProdDetailFromProdInfo(ProductBarcodeInf productBarcodeInf,
                                                  String storeId) {
    ProductDetail productDetail = new ProductDetail();
    productDetail.setProduct(productBarcodeInf.getProductInfo());
    productDetail.setStoreId(storeId);
    productDetail.setProductUnits(new ArrayList<>());
    productDetail.setAdditionalCodes(new ArrayList<>());
    productDetail.setAdditionalExpense(null);
    productDetail.setPackages(new ArrayList<>());
    productDetail.setProductId(productBarcodeInf.getProductInfo().getId());
    productDetail.setWholesalePrice(productBarcodeInf.getProductPrice().getWholesalePrice());
    productDetail.setCategory(productBarcodeInf.getCategory());
    productDetail.setSubCategory(productBarcodeInf.getSubCategory());
    productDetail.setNumberOnScale(productBarcodeInf.getProductPrice().getNumberOnScale());
    return productDetail;
  }

  private String getCurrentDate() {
    Date currentDate = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String date = simpleDateFormat.format(currentDate);
    date = date.replaceAll(" ", "T");
    return date;
  }

  private DecomissionedProduct setDecProductDetails(ProductBarcodeInf productBarcodeInf,
                                                    Long quantity) {
    DecomissionedProduct decomissionedProduct = new DecomissionedProduct();
    decomissionedProduct.setSellingPrice(productBarcodeInf.getProductPrice().getSellingPrice());
    decomissionedProduct.setArrivalCost(productBarcodeInf.getProductPrice().getArrivalCost());
    decomissionedProduct.setPrice(100L);
    decomissionedProduct.setQuantity(quantity);
    decomissionedProduct.setStockIn(true);
    decomissionedProduct.setType(productBarcodeInf.getProductInfo().getType());
    return decomissionedProduct;
  }

  private DebitedProduct setDebProductDetails(ProductBarcodeInf productBarcodeInf, Long quantity) {
    DebitedProduct debitedProduct = new DebitedProduct();
    debitedProduct.setSellingPrice(productBarcodeInf.getProductPrice().getSellingPrice());
    debitedProduct.setArrivalCost(productBarcodeInf.getProductPrice().getArrivalCost());
    debitedProduct.setPrice(100L);
    debitedProduct.setQuantity(quantity);
    debitedProduct.setStockIn(true);


    return debitedProduct;
  }


}

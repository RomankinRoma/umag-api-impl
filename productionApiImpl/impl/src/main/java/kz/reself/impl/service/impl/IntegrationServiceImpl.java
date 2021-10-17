package kz.reself.impl.service.impl;

import static kz.reself.impl.constant.Constants.DEFAULT_PAGE_NUMBER;
import static kz.reself.impl.constant.Constants.DEFAULT_PAGE_SIZE;
import static kz.reself.impl.constant.Constants.PAGE_NAME;
import static kz.reself.impl.constant.Constants.SIZE_NAME;
import static kz.reself.impl.constant.IntegrationContants.API_VERSION;
import static kz.reself.impl.constant.IntegrationContants.AUTH_HEADER;
import static kz.reself.impl.constant.IntegrationContants.CLIENT_VERSION;
import static kz.reself.impl.constant.IntegrationContants.LOGIN_URL;
import static kz.reself.impl.constant.IntegrationContants.REST_URL;

import kz.reself.impl.model.integration.AllProducts;
import kz.reself.impl.model.integration.Debit;
import kz.reself.impl.model.integration.Decomission;
import kz.reself.impl.model.integration.DecomissionDTO;
import kz.reself.impl.model.integration.ProductBarcodeInf;
import kz.reself.impl.model.integration.ProductDTO;
import kz.reself.impl.model.integration.UmagUser;
import kz.reself.impl.service.IIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class IntegrationServiceImpl implements IIntegrationService {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${basic.auth}")
  private String basicAuth;

  @Override
  public UmagUser login() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(AUTH_HEADER, basicAuth);
    httpHeaders.add(API_VERSION, "1.4");
    httpHeaders.add(CLIENT_VERSION, "angular_cabinet_0.24.06");
    HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);
    ResponseEntity<UmagUser> responseEntity =
        restTemplate.exchange(LOGIN_URL, HttpMethod.GET, entity, UmagUser.class);
    return responseEntity.getBody();
  }

  @Override
  public List<ProductDTO> getAllProducts(Map<String, String> allParams) {
    int pageNumber = DEFAULT_PAGE_NUMBER;

    int pageSize = DEFAULT_PAGE_SIZE;
    String storeId = "1";
    if (allParams.containsKey(PAGE_NAME)) {
      pageNumber = Integer.parseInt(allParams.get(PAGE_NAME));
    }

    if (allParams.containsKey(SIZE_NAME)) {
      pageSize = Integer.parseInt(allParams.get(SIZE_NAME));
    }
    if (allParams.containsKey("storeId")) {
      storeId = allParams.get("storeId");
    }

    HttpEntity<String> entity = new HttpEntity<>("", getHeaders());
    ResponseEntity<AllProducts> responseEntity = null;
    String url = REST_URL + "/nom/product/all?first=" + pageNumber
        + "&pageSize=" + pageSize
        + "&storeId=" + storeId;
    responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, AllProducts.class);
    return responseEntity.getBody().getList();
  }

  @Override
  public ProductBarcodeInf getProductById(String barcode, String storeId) {
    HttpEntity<String> entity = new HttpEntity<>("", getHeaders());
    ResponseEntity<ProductBarcodeInf> responseEntity = null;
    String url = REST_URL + "/nom/product/" + barcode + "?storeId=" + storeId;
    responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, ProductBarcodeInf.class);
    return responseEntity.getBody();
  }

  @Override
  public Object getDecomisionDebitByStoreId(Map<String, String> allParams) {
    int pageNumber = DEFAULT_PAGE_NUMBER;

    int pageSize = DEFAULT_PAGE_SIZE;
    String storeId = "1";
    if (allParams.containsKey(PAGE_NAME)) {
      pageNumber = Integer.parseInt(allParams.get(PAGE_NAME));
    }

    if (allParams.containsKey(SIZE_NAME)) {
      pageSize = Integer.parseInt(allParams.get(SIZE_NAME));
    }
    if (allParams.containsKey("storeId")) {
      storeId = allParams.get("storeId");
    }
    String to = null;
    if (allParams.containsKey("to")) {
      to = allParams.get("to");
    }

    String from = null;
    if (allParams.containsKey("from")) {
      from = allParams.get("from");
    }

    HttpEntity<String> entity = new HttpEntity<>("", getHeaders());
    ResponseEntity<Object> responseEntity = null;
    String url = REST_URL + "/opr/decomissiondebit?first=" + pageNumber
        + "&pageSize=" + pageSize
        + "&from=" + from
        + "&to=" + to
        + "&storeId=" + storeId;
    responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
    return responseEntity.getBody();
  }

  @Override
  public DecomissionDTO getDecomisionByStoreId(String id, String storeId) {
    HttpEntity<String> entity = new HttpEntity<>("", getHeaders());
    ResponseEntity<DecomissionDTO> responseEntity = null;
    String url = REST_URL + "/opr/decomission/" + id + "/products?storeId=" + storeId;
    responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, DecomissionDTO.class);
    return responseEntity.getBody();
  }

  @Override
  public Object getDebitByStoreId(String id, String storeId) {
    HttpEntity<String> entity = new HttpEntity<>("", getHeaders());
    ResponseEntity<Object> responseEntity = null;
    String url = REST_URL + "/opr/debit/" + id + "/products?storeId=" + storeId;
    responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
    return responseEntity.getBody();
  }

  @Override
  public Object createDebit(Debit debit, String storeId) {
    HttpEntity<Debit> entity = new HttpEntity<>(debit, getHeaders());
    ResponseEntity<Object> responseEntity = null;
    String url = REST_URL + "/opr/debit/create?storeId=" + storeId;
    responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
    return responseEntity.getBody();
  }

  @Override
  public Object createDecommission(Decomission decomission, String storeId) {
    HttpEntity<Decomission> entity = new HttpEntity<>(decomission, getHeaders());
    ResponseEntity<Object> responseEntity = null;
    String url = REST_URL + "/opr/decomission/create?storeId=" + storeId;
    responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
    return responseEntity.getBody();
  }


  @Override
  public ResponseEntity<HttpStatus> deleteDebit(Long id, String storeId) {
    HttpEntity<String> entity = new HttpEntity<>("", getHeaders());
    ResponseEntity<Object> responseEntity = null;
    String url = REST_URL + "/opr/debit/remove/" + id + "?storeId=" + storeId;
    responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, Object.class);
    return ResponseEntity.ok(responseEntity.getStatusCode());
  }

  @Override
  public ResponseEntity<HttpStatus> deleteDecomission(Long id, String storeId) {
    HttpEntity<String> entity = new HttpEntity<>("", getHeaders());
    ResponseEntity<Object> responseEntity = null;
    String url = REST_URL + "/opr/decomission/remove/" + id + "?storeId=" + storeId;
    responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, Object.class);
    return ResponseEntity.ok(responseEntity.getStatusCode());
  }

  public HttpHeaders getHeaders() {
    UmagUser umagUser = login();
    HttpHeaders headers = new HttpHeaders();
    headers.add(AUTH_HEADER, umagUser.sessionToken);
    headers.add(API_VERSION, "1.4");
    headers.add(CLIENT_VERSION, "angular_cabinet_0.24.06");
    return headers;
  }
}

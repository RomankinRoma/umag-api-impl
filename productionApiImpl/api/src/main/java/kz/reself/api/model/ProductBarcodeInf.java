package kz.reself.api.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductBarcodeInf {
  private Long storeId;
  private String barcode;
  private ProductInfo productInfo;
  private Category category;
  private Category subCategory;
  private ProductPrice productPrice;
  private List<Object> aliases;
  private List<Object> productUnits;
  private Object quickProduct;
  private Object supplier;
}

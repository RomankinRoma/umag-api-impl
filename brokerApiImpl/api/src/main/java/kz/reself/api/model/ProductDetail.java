package kz.reself.api.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetail {
  public List<Object> productUnits;
  public ProductInfo product;
  public List<Object> additionalCodes;
  public Long arrivalCost;
  public double sellingPrice;
  public double wholesalePrice;
  public Long markup;
  public List<Object> packages;
  public boolean isPackage;
  public boolean isService;
  public String additionalExpense;
  public String storeId;
  public Long productId;
  public boolean isHiddenOnScale;
  public Long numberOnScale;
  public Long stockQuantity;
  public Category category;
  public Category subCategory;
}


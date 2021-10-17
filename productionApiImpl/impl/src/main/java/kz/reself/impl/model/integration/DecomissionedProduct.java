package kz.reself.impl.model.integration;

import lombok.Data;

@Data
public class DecomissionedProduct {
  public ProductDetail productDetail;
  public Long price;
  public double sellingPrice;
  public double arrivalCost;
  public Long quantity;
  public boolean stockIn;
  public String type;
}

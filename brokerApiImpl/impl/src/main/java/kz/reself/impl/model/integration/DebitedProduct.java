package kz.reself.impl.model.integration;

import lombok.Data;

@Data
public class DebitedProduct {
  public ProductDetail productDetail;
  public Long price;
  public double sellingPrice;
  public Long arrivalCost;
  public Long quantity;
  public boolean stockIn;
}

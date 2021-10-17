package kz.reself.impl.model.integration;

import lombok.Data;

@Data
public class ProductPrice {
  public String storeId;
  public Long barcode;
  public double arrivalCost;
  public double sellingPrice;
  public double wholesalePrice;
  public boolean isHiddenOnScale;
  public Long numberOnScale;
  public double additionalExpense;
  public String editTime;
}


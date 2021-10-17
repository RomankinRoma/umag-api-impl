package kz.reself.api.model;

import lombok.Data;

import java.util.List;

@Data
public class DecomissionDTO {
  public Long id;
  public Long revisionId;
  public double amount;
  public double amountByArrivalCost;
  public double amountBySellingPrice;
  public Long storeId;
  public Long userId;
  public String time;
  public String createDate;
  public String comment;
  public Long type;
  public String typeName;
  public boolean stockIn;
  public boolean isEditable;
  public List<DecomissionedProduct> decomissionedProducts;
}


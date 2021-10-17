package kz.reself.impl.model.integration;

import lombok.Data;

@Data
public class ProductInfo {
  public Long auxAttrTypeID;
  public Long id;
  public Long storeGroupId;
  public String code;
  public long barcode;
  public String name;
  public String editTime;
  public Long categoryId;
  public Long subCategoryId;
  public String type;
  public Long measure;
  public boolean isDeleted;
}

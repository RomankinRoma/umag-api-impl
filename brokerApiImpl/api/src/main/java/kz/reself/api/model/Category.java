package kz.reself.api.model;

import lombok.Data;

@Data
public class Category {
  public Long id;
  public String name;
  public Long storeGroupId;
  public Long parentId;
  public Long globalCategory;
  public boolean isDeleted;
  public String editTime;
  public String createTime;
}

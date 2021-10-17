package kz.reself.api.model;

import lombok.Data;

import java.util.Date;

@Data
public class Category {
  public Long id;
  public String name;
  public Long storeGroupId;
  public Long parentId;
  public Long globalCategory;
  public boolean isDeleted;
  public Date editTime;
  public Date createTime;
}

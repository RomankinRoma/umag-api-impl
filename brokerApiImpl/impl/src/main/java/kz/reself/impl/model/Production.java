package kz.reself.impl.model;

import lombok.Data;

import java.util.Date;

@Data
public class Production {
  private Long id;

  private Long storeId;

  private Long mbId;

  private Double additionalCost;

  private Long multiplier;

  private boolean isDeleted;

  private boolean isDraft;

  private Date createdTime;

  private Date editTime;

  private ProductStatus productStatus;

}

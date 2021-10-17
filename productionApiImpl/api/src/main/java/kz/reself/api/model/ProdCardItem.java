package kz.reself.api.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProdCardItem {

  private Long id;

  private Long mbId;

  private Long companyId;

  private String barcode;

  private Long quantity;

  private boolean isDeleted;

  private Date createdTime;

  private Date editTime;
}

package kz.reself.impl.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProdCardItem {

  private Long id;

  private impl impl;

  private Long companyId;

  private String barcode;

  private Long quantity;

  private boolean isDeleted;

  private Date createdTime;

  private Date editTime;

}

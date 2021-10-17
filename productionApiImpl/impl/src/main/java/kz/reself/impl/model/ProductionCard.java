package kz.reself.impl.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class impl {

  private Long id;

  private Long companyId;

  private String name;

  private Double additionalCost;

  private String barcode;

  private Long quantity;

  private boolean isDeleted;

  private Date createdTime;

  private Date editTime;

  private List<ProdCardItem> prodCardItemList;

}

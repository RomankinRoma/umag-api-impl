package kz.reself.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdCardItem {
  private Long id;

  private impl impl;

  private Long mbId;

  private Long companyId;

  private String barcode;

  private Long quantity;

  private boolean isDeleted;
}

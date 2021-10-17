package kz.reself.api.model;

import kz.reself.api.model.Production;
import lombok.Data;

@Data
public class CrmRequest {
  private Decomission decomission;
  private Debit debit;
  private Production production;
}

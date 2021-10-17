package kz.reself.impl.model;

import kz.reself.impl.model.integration.Debit;
import kz.reself.impl.model.integration.Decomission;
import lombok.Data;

@Data
public class CrmRequest {
  private Decomission decomission;
  private Production production;
  private Debit debit;
}

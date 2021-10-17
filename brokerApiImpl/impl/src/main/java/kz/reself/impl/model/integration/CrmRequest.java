package kz.reself.impl.model.integration;

import kz.reself.impl.model.Production;
import lombok.Data;

@Data
public class CrmRequest {
  private Decomission decomission;
  private Debit debit;
  private Production production;
}

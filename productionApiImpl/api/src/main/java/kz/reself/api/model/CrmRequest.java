package kz.reself.api.model;

import lombok.Data;

@Data
public class CrmRequest {
  private Decomission decomission;
  private Production production;
  private Debit debit;
}

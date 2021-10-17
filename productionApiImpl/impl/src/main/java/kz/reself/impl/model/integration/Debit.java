package kz.reself.impl.model.integration;

import lombok.Data;

import java.util.List;

@Data
public class Debit {
  public boolean stockIn;
  public List<DebitedProduct> debitedProducts;
  public String time;
  public String comment;
  public String type;
}


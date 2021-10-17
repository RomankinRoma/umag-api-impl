package kz.reself.api.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Debit {
  public boolean stockIn;
  public List<DebitedProduct> debitedProducts;
  public Date time;
  public String comment;
  public String type;
}


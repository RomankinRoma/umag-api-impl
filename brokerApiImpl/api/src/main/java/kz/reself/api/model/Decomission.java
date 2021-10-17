package kz.reself.api.model;

import lombok.Data;

import java.util.List;

@Data
public class Decomission {
  public boolean stockIn;
  public List<DecomissionedProduct> decomissionedProducts;
  public String time;
  public String comment;
  public String type;

}

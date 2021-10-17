package kz.reself.impl.model.integration;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ProductDTO {
  private Long id;
  private Long storeId;
  private String barcode;
  private String name;
  private Long type;
  private Long measure;
  private Double arrivalCost;
  private Double sellingPrice;
  private Integer numberOnScale;
  private Timestamp editTime;
  private String supplierName;
  private String aliases;
  private List<String> aliasList;
  private Double margin;
  private Double markup;
}

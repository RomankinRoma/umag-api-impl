package kz.reself.impl.model.integration;

import lombok.Data;

import java.util.List;

@Data
public class AllProducts {
  private Long totalCount;
  private Long fetchedCount;
  private List<ProductDTO> list;
}

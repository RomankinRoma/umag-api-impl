package kz.reself.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Production   {
  private Long id;

  private Long storeId;

  private Long mbId;

  private Double additionalCost;

  private Long multiplier;

  private boolean isDeleted;

  private boolean isDraft;

  private ProductStatus productStatus;

}

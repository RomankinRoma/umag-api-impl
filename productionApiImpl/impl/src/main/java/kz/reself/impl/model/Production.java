package kz.reself.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "production")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Production extends Auditable {
  @Id
  @GeneratedValue(generator = "production_id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(allocationSize = 1,
      name = "production_id_seq",
      sequenceName = "production_id_seq")
  private Long id;

  private Long storeId;

  private Long mbId;

  private Double additionalCost;

  private Long multiplier;

  private boolean isDeleted;

  private boolean isDraft;

  @Enumerated(EnumType.STRING)
  private ProductStatus productStatus;

}

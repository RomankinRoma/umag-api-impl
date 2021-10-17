package kz.reself.impl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "production_card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class impl extends Auditable {
  @Id
  @GeneratedValue(generator = "prod_card_id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(allocationSize = 1,
      name = "prod_card_id_seq",
      sequenceName = "prod_card_id_seq")
  private Long id;

  private Long companyId;

  private String name;

  private Double additionalCost;

  private String barcode;

  private Long quantity;

  private boolean isDeleted;

  @OneToMany(mappedBy = "impl", fetch = FetchType.LAZY)
  @JsonIgnoreProperties(value = {"impl", "hibernateLazyInitializer"})
  private List<ProdCardItem> prodCardItemList;
}

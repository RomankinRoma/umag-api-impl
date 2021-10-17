package kz.reself.impl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "production_card_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdCardItem extends Auditable {
  @Id
  @GeneratedValue(generator = "prod_card_item_id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "prod_card_item_id_seq", sequenceName = "prod_card_item_id_seq", allocationSize = 1)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "mb_id", updatable = false, insertable = false)
  @JsonIgnore
  private impl impl;

  @Column(name = "mb_id")
  private Long mbId;

  private Long companyId;

  private String barcode;

  private Long quantity;

  private boolean isDeleted;
}

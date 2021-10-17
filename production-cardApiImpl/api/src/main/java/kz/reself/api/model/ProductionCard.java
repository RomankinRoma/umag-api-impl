package kz.reself.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class impl {

  private Long id;

  private Long companyId;

  private String name;

  private Double additionalCost;

  private String barcode;

  private Long quantity;

  private boolean isDeleted;

  private List<ProdCardItem> prodCardItemList;
}

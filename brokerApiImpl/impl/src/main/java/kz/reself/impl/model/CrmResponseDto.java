package kz.reself.impl.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrmResponseDto {
  private Production production;
  private Integer statusCode;
  private String message;

  public void buildErrorCrm(String message) {
    this.setStatusCode(500);
    this.setMessage(message);
  }
}

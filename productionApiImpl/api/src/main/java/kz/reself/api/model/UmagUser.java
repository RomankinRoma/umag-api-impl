package kz.reself.api.model;

import lombok.Data;

@Data
public class UmagUser {
  public Long id;
  public Long userId;
  public Long posId;
  public Long supportId;
  public boolean isActive;
  public boolean restrictMode;
  public String sessionToken;
  public String oldToken;
  public String createTime;
  public String refreshTime;
  public String userAgent;
  public String ip;
  public Long companyId;
}

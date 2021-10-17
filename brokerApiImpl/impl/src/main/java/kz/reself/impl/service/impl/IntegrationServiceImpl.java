package kz.reself.impl.service.impl;


import static kz.reself.impl.constant.IntegrationContants.API_VERSION;
import static kz.reself.impl.constant.IntegrationContants.AUTH_HEADER;
import static kz.reself.impl.constant.IntegrationContants.CLIENT_VERSION;
import static kz.reself.impl.constant.IntegrationContants.LOGIN_URL;
import static kz.reself.impl.constant.IntegrationContants.REST_URL;

import kz.reself.impl.model.integration.Debit;
import kz.reself.impl.model.integration.Decomission;
import kz.reself.impl.model.integration.UmagUser;
import kz.reself.impl.service.IIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IntegrationServiceImpl implements IIntegrationService {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${basic.auth.url}")
  private String authUrl;
  @Value("${basic.auth.api-version}")
  private String authApiVersion;
  @Value("${basic.auth.client-version}")
  private String authClientVersion;

  @Override
  public UmagUser login() {
    HttpHeaders httpHeaders = getBasicHeaders();
    httpHeaders.add(AUTH_HEADER, authUrl);
    HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);
    ResponseEntity<UmagUser> responseEntity =
        restTemplate.exchange(LOGIN_URL, HttpMethod.GET, entity, UmagUser.class);
    return responseEntity.getBody();
  }


  @Override
  public ResponseEntity<?> createDebit(Debit debit, String storeId) {
    HttpEntity<Debit> entity = new HttpEntity<>(debit, getHeaders());
    ResponseEntity<Object> responseEntity = null;
    String url = REST_URL + "/opr/debit/create?storeId=" + storeId;
    responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
    return responseEntity;
  }

  @Override
  public ResponseEntity<?> createDecommission(Decomission decomission, String storeId) {
    HttpEntity<Decomission> entity = new HttpEntity<>(decomission, getHeaders());
    ResponseEntity<Object> responseEntity = null;
    String url = REST_URL + "/opr/decomission/create?storeId=" + storeId;
    responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
    return responseEntity;
  }



  private HttpHeaders getHeaders() {
    UmagUser umagUser = login();
    HttpHeaders headers = new HttpHeaders();
    headers.add(AUTH_HEADER, umagUser.sessionToken);
    headers.add(API_VERSION, "1.4");
    headers.add(CLIENT_VERSION, "angular_cabinet_0.24.06");
    return headers;
  }

  private HttpHeaders getBasicHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(API_VERSION, authApiVersion);
    headers.add(CLIENT_VERSION, authClientVersion);
    return headers;
  }
}

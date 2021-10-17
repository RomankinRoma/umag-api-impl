package kz.reself.impl.service.impl;

import static kz.reself.impl.constant.Constants.RECEIVE_EXCHANGE;
import static kz.reself.impl.constant.Constants.RECEIVE_ROUTING_KEY;

import kz.reself.impl.model.CrmResponseDto;
import kz.reself.impl.model.Production;
import kz.reself.impl.model.integration.CrmRequest;
import kz.reself.impl.model.integration.Debit;
import kz.reself.impl.model.integration.DebitedProduct;
import kz.reself.impl.model.integration.Decomission;
import kz.reself.impl.model.integration.DecomissionedProduct;
import kz.reself.impl.service.CrmService;
import kz.reself.impl.service.IIntegrationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("CrmServiceStub")
public class CrmServiceStubImpl implements CrmService {

  @Autowired
  private RabbitTemplate amqpTemplate;
  @Autowired
  private IIntegrationService integrationService;

  @Override
  public void sendToCrm(CrmRequest crmRequest) {
    ResponseEntity<?> responseEntity = null;
    Decomission decomission = crmRequest.getDecomission();
    Production production = crmRequest.getProduction();
    CrmResponseDto crmResponseDto = CrmResponseDto.builder()
        .production(production)
        .build();
    DecomissionedProduct decomissionedProduct;
    String storeId = null;
    if (!decomission.getDecomissionedProducts().isEmpty()) {
      decomissionedProduct = decomission.getDecomissionedProducts().get(0);
      storeId = decomissionedProduct.getProductDetail().getStoreId();
    }
    try {
      responseEntity = integrationService.createDecommission(decomission, storeId);
      crmResponseDto.setStatusCode(responseEntity.getStatusCodeValue());
      crmResponseDto.setMessage(String.valueOf(responseEntity.getStatusCodeValue()));
    } catch (Exception e) {
      crmResponseDto.buildErrorCrm(e.getMessage());
    }

    amqpTemplate.convertAndSend(RECEIVE_EXCHANGE, RECEIVE_ROUTING_KEY, crmResponseDto);
  }

  @Override
  public void sendDebitToCrm(CrmRequest crmRequest) throws Exception {
    ResponseEntity<?> responseEntity = null;
    Debit debit = crmRequest.getDebit();
    Production production = crmRequest.getProduction();
    CrmResponseDto crmResponseDto = CrmResponseDto.builder()
        .production(production)
        .build();
    DebitedProduct debitedProduct;
    String storeId = null;
    if (!debit.getDebitedProducts().isEmpty()) {
      debitedProduct = debit.getDebitedProducts().get(0);
      storeId = debitedProduct.getProductDetail().getStoreId();
    }
    try {
      responseEntity = integrationService.createDebit(debit, storeId);
      crmResponseDto.setStatusCode(responseEntity.getStatusCodeValue());
      crmResponseDto.setMessage(String.valueOf(responseEntity.getStatusCodeValue()));
    } catch (Exception e) {
      crmResponseDto.buildErrorCrm(e.getMessage());
    }

    amqpTemplate.convertAndSend(RECEIVE_EXCHANGE, RECEIVE_ROUTING_KEY, crmResponseDto);
  }


}

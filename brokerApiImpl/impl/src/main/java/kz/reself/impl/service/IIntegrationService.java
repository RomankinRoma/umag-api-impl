package kz.reself.impl.service;


import kz.reself.impl.model.integration.Debit;
import kz.reself.impl.model.integration.Decomission;
import kz.reself.impl.model.integration.UmagUser;
import org.springframework.http.ResponseEntity;

public interface IIntegrationService {

  UmagUser login();

  ResponseEntity<?> createDebit(Debit debit, String storeId);

  ResponseEntity<?> createDecommission(Decomission decomision, String storeId);

}

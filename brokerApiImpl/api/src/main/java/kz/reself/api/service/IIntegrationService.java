package kz.reself.api.service;


import kz.reself.api.model.Debit;
import kz.reself.api.model.Decomission;
import kz.reself.api.model.UmagUser;
import org.springframework.http.ResponseEntity;

public interface IIntegrationService {

  UmagUser login();

  ResponseEntity<?> createDebit(Debit debit, String storeId);

  ResponseEntity<?> createDecommission(Decomission decomision, String storeId);

}

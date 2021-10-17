package kz.reself.api.service;

import kz.reself.api.model.CrmRequest;

public interface CrmService {
  void sendToCrm(CrmRequest production) throws Exception;

  void sendDebitToCrm(CrmRequest production) throws Exception;
}

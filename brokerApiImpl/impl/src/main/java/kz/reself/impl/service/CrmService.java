package kz.reself.impl.service;

import kz.reself.impl.model.integration.CrmRequest;

public interface CrmService {
  void sendToCrm(CrmRequest production) throws Exception;

  void sendDebitToCrm(CrmRequest production) throws Exception;
}

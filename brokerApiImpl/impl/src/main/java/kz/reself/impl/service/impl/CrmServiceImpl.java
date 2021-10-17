package kz.reself.impl.service.impl;

import kz.reself.impl.model.integration.CrmRequest;
import kz.reself.impl.service.CrmService;
import org.springframework.stereotype.Service;

@Service("CrmService")
public class CrmServiceImpl implements CrmService {
  @Override
  public void sendToCrm(CrmRequest crmRequest) throws Exception {
    throw new Exception("This method doesn't implemented");
  }

  @Override
  public void sendDebitToCrm(CrmRequest production) throws Exception {

  }
}

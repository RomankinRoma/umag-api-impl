package kz.reself.impl.service.impl;

import kz.reself.impl.model.integration.CrmRequest;
import kz.reself.impl.service.CrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQConsumer {

  private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

  @Autowired
  @Qualifier("CrmServiceStub")// todo change to real service
  private CrmService crmService;

  @RabbitListener(queues = "general.queue")
  public void receivedMessage(CrmRequest crmRequest) throws Exception {
    logger.info("Received message from production: " + crmRequest);

    crmService.sendToCrm(crmRequest);
  }

  @RabbitListener(queues = "debit.queue")
  public void receivedMessageFromDebit(CrmRequest crmRequest) throws Exception {
    logger.info("Received message from production: " + crmRequest);

    crmService.sendDebitToCrm(crmRequest);
  }
}


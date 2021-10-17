package kz.reself.impl.service;

import kz.reself.impl.model.CrmResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQConsumer {

  private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

  @Autowired
  private IProductionService productionService;

  @RabbitListener(queues = "receive.queue")
  public void receivedMessage(CrmResponseDto s) {
    logger.info("Received message from broker" + s);
    productionService.receiveResponse(s);
  }
}


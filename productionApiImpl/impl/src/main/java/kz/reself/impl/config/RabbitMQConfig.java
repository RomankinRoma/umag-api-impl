package kz.reself.impl.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RabbitMQConfig {
  @Bean
  DirectExchange deadLetterExchange() {
    return new DirectExchange("deadLetterExchange");
  }

  @Bean
  DirectExchange exchange() {
    return new DirectExchange("decomission");
  }

  @Bean
  DirectExchange receiveExchange() {
    return new DirectExchange("receiveExchange");
  }

  @Bean
  Queue deadLetterQueue() {
    return QueueBuilder.durable("deadLetter.queue").build();
  }

  @Bean
  Queue queue() {
    return QueueBuilder.durable("general.queue")
        .withArgument("x-dead-letter-exchange", "deadLetterExchange")
        .withArgument("x-dead-letter-routing-key", "deadLetter").build();
  }

  @Bean
  DirectExchange exchangeDebit() {
    return new DirectExchange("debit");
  }

  @Bean
  Queue queueDebit() {
    return QueueBuilder.durable("debit.queue")
        .withArgument("x-dead-letter-exchange", "deadLetterExchange")
        .withArgument("x-dead-letter-routing-key", "deadLetter").build();
  }

  @Bean
  Binding bindingDebit() {
    return BindingBuilder.bind(queueDebit()).to(exchangeDebit()).with("debit");
  }

  @Bean
  Queue receiveQueue() {
    return QueueBuilder.durable("receive.queue")
        .withArgument("x-dead-letter-exchange", "deadLetterExchange")
        .withArgument("x-dead-letter-routing-key", "deadLetter").build();
  }

  @Bean
  Binding DLQbinding() {
    return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("deadLetter");
  }

  @Bean
  Binding binding() {
    return BindingBuilder.bind(queue()).to(exchange()).with("general");
  }

  @Bean
  Binding receiveBinding() {
    return BindingBuilder.bind(receiveQueue()).to(receiveExchange()).with("receiveProd");
  }


  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    return rabbitTemplate;
  }

}

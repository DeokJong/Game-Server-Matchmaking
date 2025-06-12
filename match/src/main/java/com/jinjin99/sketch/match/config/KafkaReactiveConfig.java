package com.jinjin99.sketch.match.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaReactiveConfig {
  private final KafkaProperties kafkaProperties;

  @Bean
  public ReactiveKafkaProducerTemplate<String, Object> kafkaProducer() {
    Map<String, Object> producerProps = kafkaProperties.buildProducerProperties();
    SenderOptions<String, Object> senderOptions = SenderOptions.create(producerProps);

    return new ReactiveKafkaProducerTemplate<>(senderOptions);
  }

  @Bean
  public ReactiveKafkaConsumerTemplate<String, Object> kafkaConsumer() {
    Map<String, Object> consumerProps = kafkaProperties.buildConsumerProperties();
    ReceiverOptions<String, Object> receiverOptions = ReceiverOptions.create(consumerProps);

    return new ReactiveKafkaConsumerTemplate<>(receiverOptions);
  }
}

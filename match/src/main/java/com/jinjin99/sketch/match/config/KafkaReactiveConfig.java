package com.jinjin99.sketch.match.config;

import com.jinjin99.sketch.match.dto.MatchEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

import java.util.Collections;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaReactiveConfig {
  private final KafkaProperties kafkaProperties;

  @Bean
  public ReactiveKafkaProducerTemplate<String, MatchEvent> kafkaProducer() {
    Map<String, Object> producerProps = kafkaProperties.buildProducerProperties();
    SenderOptions<String, MatchEvent> senderOptions = SenderOptions.<String, MatchEvent>create(producerProps).maxInFlight(50);

    return new ReactiveKafkaProducerTemplate<>(senderOptions);
  }

  @Bean
  public ReactiveKafkaConsumerTemplate<String, MatchEvent> kafkaConsumer() {
    Map<String, Object> consumerProps = kafkaProperties.buildConsumerProperties();
    ReceiverOptions<String, MatchEvent> receiverOptions = ReceiverOptions.<String, MatchEvent>create(consumerProps)
            .subscription(Collections.singleton("match"));

    return new ReactiveKafkaConsumerTemplate<>(receiverOptions);
  }
}

package com.jinjin99.sketch.match.consumer;

import com.jinjin99.sketch.match.dto.MatchEvent;
import com.jinjin99.sketch.match.service.MatchEventHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MatchEventConsumer {
  private final ReactiveKafkaConsumerTemplate<String, MatchEvent> kafkaConsumer;
  private final MatchEventHandler matchEventHandler;

  @PostConstruct
  public void matchEventSubscribe() {
    kafkaConsumer.receive()
            .doOnNext(record -> log.debug("Received match event: key={}, value={}", record.key(), record.value()))
            .flatMap(matchEventHandler::routeMatchEvent)
            .subscribe();
  }

}

package com.jinjin99.sketch.match.producer;

import com.jinjin99.sketch.match.dto.MatchEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class MatchEventProducerImpl implements MatchEventProducer {

  private static final String MatchTOPIC = "match";
  private final ReactiveKafkaProducerTemplate<String, Object> kafkaProducer;

  @Override
  public Mono<Integer> produceMatchStartEvent(String playerUUID, String playerNickname) {
    return this.produceEvent(playerUUID, MatchEvent.builder().playerNickname(playerNickname).type(MatchEvent.MatchEventType.MATCH_START).build());
  }

  @Override
  public Mono<Integer> produceMatchCancelEvent(String playerUUID) {
    return this.produceEvent(playerUUID, MatchEvent.builder().type(MatchEvent.MatchEventType.MATCH_CANCEL).build());
  }

  /**
   * Kafka에 이벤트를 발행합니다.
   * 이벤트 발행에 성공한다면, 발행된 이벤트의 파티션 번호를 반환합니다.
   * 발행에 실패하면 -1을 반환합니다.
   *
   * @param playerUUID - 플레이어의 UUID
   * @param value      - 이벤트의 값
   * @return Mono<Integer> - 발행된 이벤트의 파티션 번호
   */
  private Mono<Integer> produceEvent(String playerUUID, Object value) {
    return kafkaProducer.send(new ProducerRecord<>(MatchTOPIC, playerUUID, value)).map(result -> result.recordMetadata().partition()).onErrorResume(e -> {
      log.debug("Failed to send message to Kafka topic: {}, key: {}, value: {}, error: {}", "match", playerUUID, value, e.getMessage());
      return Mono.just(-1);
    });
  }
}

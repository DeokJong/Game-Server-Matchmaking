package com.jinjin99.sketch.match.producer;

import reactor.core.publisher.Mono;

/**
 * MatchEventProducer 인터페이스는 매칭 이벤트를 발행하는 메소드를 정의합니다.
 * 이 인터페이스는 매칭 시작 및 취소 이벤트를 Kafka에 발행하는 기능을 제공합니다.
 *
 * 내부 구현은 ReactiveKafkaProducerTemplate을 사용하여 논블로킹 I/O 방식 이벤트를 발행합니다.
 */
public interface MatchEventProducer {

  /**
   * MatchStartEvent를 발행합니다.
   * 이 이벤트는 매칭이 시작될 때 발생합니다.
   * @param playerUUID - 사용자의 식별 키
   * @return Mono<Integer> - MatchStartEvent를 발행하는 Mono
   */
  Mono<Integer> produceMatchStartEvent(String playerUUID, String playerNickname);

  /**
   * MatchCancelEvent를 발행합니다.
   * 이 이벤트는 매칭이 취소될 때 발생합니다.
   * @param playerSessionId - 사용자의 식별 키
   * @return Mono<Integer> - MatchCancelEvent를 발행하는 Mono
   */
  Mono<Integer> produceMatchCancelEvent(String playerSessionId);
}

package com.jinjin99.sketch.match.service;

import reactor.core.publisher.Mono;

public interface MatchingService {

  /**
   * 매칭을 시작합니다.
   *
   * @param playerNickname - 사용자의 닉네임
   * @return Mono<Integer> - 매칭 시작 이벤트를 발행하는 Mono
   */
  Mono<Integer> start(String playerNickname);

  /**
   * 매칭을 취소합니다.
   *
   * @param playerSessionId - 사용자의 세션 ID
   * @return Mono<Integer> - 매칭 취소 이벤트를 발행하는 Mono
   */
  Mono<Integer> cancel(String playerSessionId);
}

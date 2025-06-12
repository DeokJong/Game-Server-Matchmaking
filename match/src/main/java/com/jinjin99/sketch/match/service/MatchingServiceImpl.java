package com.jinjin99.sketch.match.service;

import com.jinjin99.sketch.match.producer.MatchEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {
  private final MatchEventProducer matchEventProducer;

  @Override
  public Mono<Integer> start(String playerNickname) {
    return matchEventProducer.produceMatchStartEvent(UUID.randomUUID().toString(), playerNickname);
  }

  @Override
  public Mono<Integer> cancel(String playerSessionId) {
    return null;
  }
}

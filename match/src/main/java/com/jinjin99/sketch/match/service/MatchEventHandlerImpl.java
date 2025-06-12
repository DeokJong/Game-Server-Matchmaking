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
public class MatchEventHandlerImpl implements MatchEventHandler {
  private final MatchEventProducer matchEventProducer;

  @Override
  public Mono<?> startMatch(String nickname) {
    return null;
    // return matchEventProducer.produceMatchStartEvent(UUID.randomUUID().toString(), nickname);
  }


  @Override
  public Mono<?> cancelMatch(String playerSessionId, String roomUUID) {
    return null;
  }

  @Override
  public Mono<?> transferGameServer() {
    return null;
  }
}

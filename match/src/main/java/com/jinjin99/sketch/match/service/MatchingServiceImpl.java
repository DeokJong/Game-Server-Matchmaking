package com.jinjin99.sketch.match.service;

import com.jinjin99.sketch.match.dto.Player;
import com.jinjin99.sketch.match.producer.MatchEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {
  private final MatchEventProducer matchEventProducer;

  @Override
  public Mono<?> startMatch(String nickname) {
    return matchEventProducer.produceMatchStartEvent(UUID.randomUUID().toString(), nickname);
  }


  @Override
  public Mono<?> cancelMatch(Player player, String roomUUID) {
    return null;
  }

  @Override
  public Mono<?> transferGameServer() {
    return null;
  }
}

package com.jinjin99.sketch.match.service;

import com.jinjin99.sketch.match.dto.MatchEvent;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.ReceiverRecord;

public interface MatchEventHandler {

  Mono<?> startMatch(String nickname);

  Mono<?> cancelMatch(String playerSessionId, String roomUUID);

  Mono<?> transferGameServer();

  default Mono<?> routeMatchEvent(ReceiverRecord<String, MatchEvent> record) {
    MatchEvent matchEvent = record.value();
    return switch (matchEvent.getType()) {
      case MATCH_START -> startMatch(matchEvent.getPlayer().getNickname());
      case MATCH_CANCEL -> cancelMatch(matchEvent.getPlayer().getSessionId(), "roomUUID");
      case MATCH_UPDATE -> transferGameServer();
      default -> Mono.error(new IllegalArgumentException("Unknown match event type: " + matchEvent.getType()));
    };
  }
}

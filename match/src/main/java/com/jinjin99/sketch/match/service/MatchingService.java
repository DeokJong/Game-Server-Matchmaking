package com.jinjin99.sketch.match.service;

import com.jinjin99.sketch.match.dto.Player;
import reactor.core.publisher.Mono;

public interface MatchingService {

  Mono<?> startMatch(String nickname);

  Mono<?> cancelMatch(Player player, String roomUUID);

  Mono<?> transferGameServer();
}

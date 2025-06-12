package com.jinjin99.sketch.match.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class MatchEvent {
  @NonNull
  private MatchEventType type;
  private Player player;

  public enum MatchEventType {
    MATCH_START,
    MATCH_CANCEL,
    MATCH_UPDATE,
  }
}

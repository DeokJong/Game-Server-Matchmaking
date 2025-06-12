package com.jinjin99.sketch.match.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class Player {
  private String nickname;
  private String sessionId;
}

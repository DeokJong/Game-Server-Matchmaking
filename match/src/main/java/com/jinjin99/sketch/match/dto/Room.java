package com.jinjin99.sketch.match.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class Room {
  private String status;
  private String roomUUID;
  private String type;
  private List<Player> players;
}

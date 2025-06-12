package com.jinjin99.sketch.match.controller;

import com.jinjin99.sketch.match.service.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class Controller {

  private final MatchingService matchingService;

  @PostMapping(value = "/randomMatch")
  Mono<?> matchStart(@RequestBody String nickname) {
    return matchingService.startMatch(nickname);
  }

}

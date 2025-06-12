package com.jinjin99.sketch.match.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;


@Slf4j
@Aspect
@Component
public class RestControllerLogging {

  @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
  public void restControllerBean() {
  }

  @Around("restControllerBean()")
  public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
    String classMethod = pjp.getSignature().toShortString();
    Object[] args = pjp.getArgs();

    log.debug("▶︎ [START] {} args={}", classMethod, args);
    long start = System.nanoTime();

    Object result = pjp.proceed();
    if (result instanceof Mono<?> mono) {
      return mono.doFinally(sig -> logCompletion(classMethod, start, sig));
    }
    if (result instanceof Flux<?> flux) {
      return flux.doFinally(sig -> logCompletion(classMethod, start, sig));
    }

    logCompletion(classMethod, start, null);
    return result;
  }

  private void logCompletion(String point, long startNs, SignalType signal) {
    long tookMs = (System.nanoTime() - startNs) / 1_000_000;
    if (signal == SignalType.ON_ERROR) {
      log.error("■ [ERROR] {} took={}ms", point, tookMs);
    } else {
      log.debug("◀︎ [END]   {} took={}ms", point, tookMs);
    }
  }

}

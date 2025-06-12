package com.jinjin99.sketch.match.config;


import com.jinjin99.sketch.match.dto.Room;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class ReactiveRedisTemplateConfig {

  private final String REDIS_HOST;
  private final Integer REDIS_PORT;


  public ReactiveRedisTemplateConfig(@Value("${spring.data.redis.host}") String redisHost,
                                     @Value("${spring.data.redis.port}") Integer redisPort) {
    REDIS_HOST = redisHost;
    REDIS_PORT = redisPort;

  }

  @Bean
  @Primary
  ReactiveRedisConnectionFactory reactiveConnectionFactory() {
    return new LettuceConnectionFactory(REDIS_HOST, REDIS_PORT);
  }

  @Bean
  ReactiveRedisTemplate<String, Room> matchingRoomRedisTemplate(ReactiveRedisConnectionFactory reactiveConnectionFactory) {

    Jackson2JsonRedisSerializer<Room> serializer = new Jackson2JsonRedisSerializer<>(Room.class);
    RedisSerializationContext.RedisSerializationContextBuilder<String, Room> builder = RedisSerializationContext.newSerializationContext((new StringRedisSerializer()));
    RedisSerializationContext<String, Room> context = builder.value(serializer).hashValue(serializer).hashKey(serializer).build();

    return new ReactiveRedisTemplate<>(reactiveConnectionFactory, context);
  }
}

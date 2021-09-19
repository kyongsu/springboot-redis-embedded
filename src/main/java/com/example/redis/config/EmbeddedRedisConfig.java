package com.example.redis.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@RequiredArgsConstructor
@Profile("local")
@Configuration
public class EmbeddedRedisConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RedisProperties redisProperties;

    private RedisServer redisServer;

    @PostConstruct
    public void redisServer(){
        redisServer = new RedisServer(redisProperties.getPort());
        redisServer.start();
        logger.info("RedisServer Start!!!");
    }

    @PreDestroy
    public void stopRedis(){
        if(null != redisServer){
            redisServer.stop();
            logger.info("RedisServer Stop!!!");
        }
    }

}

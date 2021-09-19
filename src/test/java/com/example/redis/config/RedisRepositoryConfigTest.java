package com.example.redis.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisRepositoryConfigTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void 레디스_문자열_테스트() throws Exception {
        //given
        final String key = "testString";
        final ValueOperations<String, String> stringStringValueOps = redisTemplate.opsForValue();

        //when
        stringStringValueOps.set(key, "1234");

        //then
        final String result = stringStringValueOps.get(key);

        logger.info("result:{}", result);

        Assertions.assertEquals("1234", result);
    }

}
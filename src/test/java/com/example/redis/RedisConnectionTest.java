package com.example.redis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisConnectionTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads(){

    }

    @Test
    public void redisConnectionTest() throws Exception {
        //given
        final String key = "a";
        final String data = "value";

        final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, data);

        //when
        final String result = valueOperations.get(key);

        //then
        Assertions.assertEquals(data, result);
    }


}
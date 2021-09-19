package com.example.redis.controller;

import com.example.redis.domain.Member;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisMembersController {

    final private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisMembersController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/redis/members/{key}")
    public Member getMember(@PathVariable String key){
        Member member = (Member) redisTemplate.opsForValue().get(key);

        if(null == member){
            throw new IllegalStateException("ID 값이 없습니다");
        }
        return member;
    }

    @PostMapping(path = "/redis/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertMember(@RequestBody Member member){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        String key = String.valueOf(member.getId());

        if(ObjectUtils.defaultIfNull(valueOperations.size(key), 0L) == 0L){
            valueOperations.set(key, member);
        } else {
           throw new DuplicateKeyException("중복 ID가 존재합니다.");
        }
        return String.format("id:%1$S, Insert Success!!", member.getId());
    }

    @PostMapping(value="/redis/members/{id}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateMember(@RequestBody Member member){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        String key = String.valueOf(member.getId());

        if(ObjectUtils.defaultIfNull(valueOperations.size(key), 0L) == 0L){
            valueOperations.set(key, member);
        }
        return String.format("id:%1$S, Update Success!!", member.getId());
    }

    @DeleteMapping("/redis/members/{key}")
    public String deleteMember(@PathVariable String key){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        if(null != valueOperations.get(key)){
            redisTemplate.delete(key);
            return "Delete, OK!!";
        } else {
            return "No User Data!!";
        }
    }

}

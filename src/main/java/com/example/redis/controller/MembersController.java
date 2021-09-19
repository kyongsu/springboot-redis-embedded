package com.example.redis.controller;

import com.example.redis.domain.Member;
import lombok.NoArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@NoArgsConstructor
public class MembersController {
    private LinkedHashMap<Long, Member> localMemroyMap = new LinkedHashMap<>();

    @GetMapping("/members/{id}")
    public Member getMember(@PathVariable Long id){
        if(null == id){
            throw new IllegalStateException("ID 값이 없습니다");
        }
        return localMemroyMap.get(id);
    }

    @GetMapping("/members")
    public LinkedHashMap<Long, Member> getAllMember(){
        return localMemroyMap;
    }

    @PostMapping(path = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertMember(@RequestBody Member member){
        System.out.println("member = " + member.getId());
        
        if(!localMemroyMap.containsKey(member.getId())){
            localMemroyMap.put(member.getId(), member);
        } else {
           throw new DuplicateKeyException("중복 ID가 존재합니다.");
        }
        return String.format("id:%1$S, Insert Success!!", String.valueOf(member.getId()));
    }

    @PostMapping("/members/{id}/update")
    public String updateMember(@RequestBody Member member){
        if(localMemroyMap.containsKey(member.getId())){
            localMemroyMap.put(member.getId(), member);
        }
        return String.format("id:%1$S, Update Success!!", String.valueOf(member.getId()));
    }

    @DeleteMapping("/members/{id}")
    public String deleteMember(@PathVariable Long id){
        if(localMemroyMap.containsKey(id)){
            localMemroyMap.remove(id);
            return "Delete, OK!!";
        } else {
            return "No User Data!!";
        }
    }

}

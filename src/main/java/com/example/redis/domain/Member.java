package com.example.redis.domain;

import com.fasterxml.jackson.core.SerializableString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {
    private long id;
    private String name;
    private String email;
    private String address;
}

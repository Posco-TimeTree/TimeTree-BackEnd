package com.example.temp.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    private String uuid;
    private String name; //유저 이름
    private String email; //유저 구글 이메일

    @Builder
    public Member(Long id, String uuid, String name, String email) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.email = email;
    }
}
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
    private String provider; //공급자 (google, facebook ...)
    private String providerId; //공급 아이디
    private String country; //유저 사용 언어
    private String profileImage; //유저 프로필 사진

    @Builder
    public Member(Long id, String uuid, String name, String email, String provider, String providerId, String country, String profileImage) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.country = country;
        this.profileImage = profileImage;
    }
}
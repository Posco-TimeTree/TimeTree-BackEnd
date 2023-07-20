package com.example.temp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUser {
    private Long id; //기본키
    private String uuid;
    private String name; //유저 이름
    private String email; //유저 구글 이메일
    private String provider; //공급자 (google, facebook ...)
    private String providerId; //공급 아이디
    private String country; //유저 사용 언어
    private String profileImage; //유저 프로필 사진
}


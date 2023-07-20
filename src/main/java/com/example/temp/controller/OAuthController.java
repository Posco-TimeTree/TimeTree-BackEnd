package com.example.temp.controller;

import com.example.temp.dto.NaverApiDto;
import com.example.temp.entity.Member;
import com.example.temp.jwt.TokenGenerator;
import com.example.temp.model.GoogleUser;
import com.example.temp.repository.MemberRepository;
import com.example.temp.service.NaverAPI;
import com.example.temp.service.OAuth2MemberService;
import com.example.temp.service.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("oauth")
public class OAuthController {
    private final static Logger logger = LoggerFactory.getLogger(OAuthController.class);
    private final OAuthService oauthService;
    private final NaverAPI naverAPI;
    private final TokenGenerator tokenGenerator;
    private final MemberRepository memberRepository;

    @Autowired
    public OAuthController(OAuthService oAuthService, TokenGenerator tokenGenerator, NaverAPI naverAPI, MemberRepository memberRepository) {
        this.oauthService = oAuthService;
        this.tokenGenerator = tokenGenerator;
        this.naverAPI = naverAPI;
        this.memberRepository = memberRepository;
    }
    @RequestMapping("google")
    public GoogleUser googleCallBack(@RequestParam("code") String code) {
        logger.info("code : " + code);
        GoogleUser googleUser = new GoogleUser();
        return googleUser;
    }

    @RequestMapping("naver/callback")
    public void callback(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletResponse response) throws IOException {
        logger.info("code : " + code);
        logger.info("state : " + state);
        String redirectUrl = "http://localhost:3000/";
        String accees_token = oauthService.getAccessToken(code,state);

        logger.info("access_token1 :  " + accees_token);
        if (accees_token.equals("")) {
            logger.info("fail 1 : access_token cannot find");
            response.sendRedirect("http://localhost:8080");
        }
        try {
            NaverApiDto naverApiDto = naverAPI.getProfile(accees_token);
            if (naverApiDto == null) {
                logger.info("fail 2 : naverApiDto is null");
                response.sendRedirect("http://localhost:8080");
            }
            Optional<Member> member = memberRepository.findByNameAndEmail(naverApiDto.getName(), naverApiDto.getEmail());
            if (member.isEmpty()) {
                String token = tokenGenerator.getToken(member.get());
            }else {
                logger.info("fail 3 : already exist member");
                response.sendRedirect("http://localhost:8080");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("access_token2 :  " + accees_token);
        response.sendRedirect(redirectUrl + "?token=" + accees_token);
    }
}



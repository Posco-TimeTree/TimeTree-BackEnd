package com.example.temp.service;

import com.example.temp.entity.Member;
import com.example.temp.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

@Service
public class OAuth2MemberService extends DefaultOAuth2UserService {
    private final static Logger logger = LoggerFactory.getLogger(OAuth2MemberService.class);
    private final MemberRepository memberRepository;

    @Autowired
    public OAuth2MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String getAccessToken(String code, String state) {
        String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=";
        apiURL += "client_secret=";
        apiURL += "&redirect_uri=http://localhost:8080/login/oauth2/code/naver";
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;

        String access_token = "";
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader bufferedReader;
            if (responseCode == 200) {
                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                throw new RuntimeException("API 요청 실패 : " + responseCode);
            }
            String inputLine;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            bufferedReader.close();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(stringBuffer.toString(), Map.class);
            access_token = (String) map.get("access_token");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return access_token;
    }

    public int checkProvider(OAuth2UserRequest userRequest) {
        String registerId = userRequest.getClientRegistration().getRegistrationId();
        if (registerId.equals("google")) {
            return 1;
        } else if (registerId.equals("naver")) {
            return 2;
        } else {
            return 0; // 이상함.
        }
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        logger.info(oAuth2User.toString());
//        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getClientId();
        logger.info("user = " + oAuth2User.getAttributes());
        String provider_id = oAuth2User.getAttribute("sub");
        String uuid = provider + "_" + provider_id;
        logger.info("unique key : " + uuid);

        String email = oAuth2User.getAttribute("email");

        String fullName = oAuth2User.getAttribute("family_name");
        fullName = fullName + oAuth2User.getAttribute("given_name");
        logger.info("user full name : " + fullName);
        Optional<Member> someone = memberRepository.findByUuid(uuid);
        if (someone.isEmpty()) {
            Member member = Member.builder()
                    .name(fullName)
                    .uuid(uuid)
                    .email(email).build();
            memberRepository.save(member);
        }
        return oAuth2User;

    }
}
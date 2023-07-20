package com.example.temp.service;

import com.example.temp.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Service
public class OAuthService {

    private final static Logger logger = LoggerFactory.getLogger(OAuth2MemberService.class);
    private final MemberRepository memberRepository;

    @Autowired
    public OAuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public String getAccessToken(String code, String state) {
        String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=kEz83Zwu754Dn_PQ0ZK2";
        apiURL += "&client_secret=mkmZetwPuq";
        apiURL += "&redirect_uri=http://localhost:8080/oauth/naver/callback";
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        logger.info("into getAccessToken method");
        String access_token = "";
        logger.info("access_token 0 : " + access_token);
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
            logger.info("map: " + map);
            access_token = (String) map.get("access_token");
        } catch (Exception e) {
            logger.info("Go to catch");
            throw new RuntimeException(e);
        }
        return access_token;
    }
}

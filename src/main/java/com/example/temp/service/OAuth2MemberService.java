package com.example.temp.service;

import com.example.temp.entity.Member;
import com.example.temp.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OAuth2MemberService extends DefaultOAuth2UserService {
    private final static Logger logger = LoggerFactory.getLogger(OAuth2MemberService.class);
    private final MemberRepository memberRepository;

    @Autowired
    public OAuth2MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        logger.info("user = " + oAuth2User.getAttributes());
        String provider = userRequest.getClientRegistration().getClientId();
        String provider_id = oAuth2User.getAttribute("sub");
        String uniqueKey = provider + "_" + provider_id;
        logger.info("unique key : " + uniqueKey);

        String email = oAuth2User.getAttribute("email");

        String fullName = oAuth2User.getAttribute("family_name");
        fullName = fullName + oAuth2User.getAttribute("given_name");
        logger.info("user full name : " + fullName);
            Member member = Member.builder()
                    .name(fullName)
                    .email(email)
                    .provider(provider)
                    .providerId(provider_id)
                    .profileImage(oAuth2User.getAttribute("picture"))
                    .country(oAuth2User.getAttribute("locale")).build();
            memberRepository.save(member);
        return oAuth2User;
    }
}
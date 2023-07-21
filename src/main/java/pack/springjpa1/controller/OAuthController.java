package pack.springjpa1.controller;

import pack.springjpa1.data.dto.MemberDto;
import pack.springjpa1.data.dto.NaverApiDto;
import pack.springjpa1.data.entity.Member;
import pack.springjpa1.data.entity.TokenBoard;
import pack.springjpa1.data.jwt.TokenGenerator;
import pack.springjpa1.data.repository.MemberRepository;
import pack.springjpa1.data.repository.TokenBoardRepository;
import pack.springjpa1.data.service.NaverAPI;
import pack.springjpa1.data.service.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private final TokenBoardRepository tokenBoardRepository;

    @Autowired
    public OAuthController(TokenBoardRepository tokenBoardRepository, OAuthService oAuthService, TokenGenerator tokenGenerator, NaverAPI naverAPI, MemberRepository memberRepository) {
        this.oauthService = oAuthService;
        this.tokenGenerator = tokenGenerator;
        this.memberRepository = memberRepository;
        this.naverAPI = naverAPI;
        this.tokenBoardRepository = tokenBoardRepository;
    }

    @RequestMapping("naver/callback")
    public MemberDto callback(@RequestParam("code") String code, @RequestParam("state") String state) throws IOException {
        logger.info("code : " + code);
        logger.info("state : " + state);
        String redirectUrl = "http://localhost:3000/";
        String accees_token = oauthService.getAccessToken(code,state);
        String token="";
        MemberDto memberDto = new MemberDto();

        logger.info("access_token1 :  " + accees_token);
        if (accees_token.equals("")) {
            logger.info("fail 1 : access_token cannot find");
        }
        try {
            NaverApiDto naverApiDto = naverAPI.getProfile(accees_token);
            logger.info("naverAPIdto : " + naverApiDto.toString());
            if (naverApiDto == null) {
                logger.info("fail 2 : naverApiDto is null");
            }
            Optional<Member> member = memberRepository.findByNameAndEmail(naverApiDto.getName(), naverApiDto.getEmail());
            if (member.isEmpty()) {
               logger.info("fail 3 : not exist member");
                Member newMember = oauthService.createMemberFromNaver(naverApiDto);
                token = tokenGenerator.getToken(newMember);
                TokenBoard tokenBoard = oauthService.createTokenBoard(newMember, token);
                logger.info("token board : " + tokenBoard);
                memberDto.setMemberId(newMember.getId());
                memberDto.setEmail(newMember.getEmail());
                memberDto.setName(newMember.getName());
                memberDto.setToken(token);
            }else {
                token = tokenGenerator.getToken(member.get());
                TokenBoard tokenBoard = oauthService.createTokenBoard(member.get(), token);
                logger.info("token board : " + tokenBoard);
                logger.info("token: " +token);
                memberDto.setMemberId(member.get().getId());
                memberDto.setEmail(member.get().getEmail());
                memberDto.setName(member.get().getName());
                memberDto.setToken(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("token : " + token);
        logger.info("memberdto : " + memberDto);
        return memberDto;
    }
}



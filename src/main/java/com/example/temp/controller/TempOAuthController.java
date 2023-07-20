//package com.example.temp.controller;
//
//import com.example.temp.dto.NaverApiDto;
//import com.example.temp.entity.Member;
//import com.example.temp.jwt.TokenGenerator;
//import com.example.temp.repository.MemberRepository;
//import com.example.temp.service.NaverAPI;
//import com.example.temp.service.OAuth2MemberService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpSession;
//import java.util.Optional;
//
//@Controller
//@CrossOrigin("*")
//public class TempOAuthController {
//    private final static Logger logger = LoggerFactory.getLogger(TempOAuthController.class);
//    private final OAuth2MemberService oAuth2MemberService;
//    private final NaverAPI naverAPI;
//    private final TokenGenerator tokenGenerator;
//    private final MemberRepository memberRepository;
//
//    @Autowired
//    public TempOAuthController(OAuth2MemberService oAuth2MemberService, TokenGenerator tokenGenerator, NaverAPI naverAPI, MemberRepository memberRepository) {
//        this.oAuth2MemberService = oAuth2MemberService;
//        this.tokenGenerator = tokenGenerator;
//        this.naverAPI = naverAPI;
//        this.memberRepository = memberRepository;
//    }
//
//
//    @GetMapping("/loginForm")
//    public String home() {
//        logger.info("로그인 페이지");
//        return "loginForm";
//    }
//
//    @GetMapping("/private")
//    public String privatePage() {
//        logger.info("사설 페이지");
//        return "privatePage";
//    }
//    @GetMapping("/admin")
//    public String adminPage() {
//        logger.info("관리자 페이지");
//        return "adminPage";
//    }
//
//    @RequestMapping("callback")
//    public String callback(@RequestParam("code") String code,
//                           @RequestParam("state") String state,
//                           Model model,
//                           HttpSession session) {
//        String accees_token = oAuth2MemberService.getAccessToken(code, state);
//        if (accees_token.equals("")) {
//            return "index";
//        }
//        try {
//            NaverApiDto naverApiDto = naverAPI.getProfile(accees_token);
//            if (naverApiDto == null) {
//                return "index";
//            }
//            Optional<Member> member = memberRepository.findByNameAndEmail(naverApiDto.getName(), naverApiDto.getEmail());
//            if (member.isEmpty()) {
//                String token = tokenGenerator.getToken(member.get());
//                session.setAttribute("token", token);
//            }else {
////                model.addAllAttributes("member", naverApiDto);
//                return "index";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "privatePage";
//    }
//}
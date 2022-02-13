package com.yongin.whichSunday.common;

import com.yongin.whichSunday.member.service.MemberService;
import com.yongin.whichSunday.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    MemberService memberService;

    @GetMapping("/signUp")
    public String signUpForm(MemberVO memberVO) {
        return "login/signUpForm";
    }

    @PostMapping("/signUp")
    public String signUp(MemberVO memberVO) {
        MemberVO savedMember = memberService.save(memberVO);

        System.out.println("memberVO = " + memberVO);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(MemberVO memberVO) {
        //로그인 체크
        return "";
    }
}

package com.yongin.whichSunday.common.login;

import com.yongin.whichSunday.common.login.form.LoginForm;
import com.yongin.whichSunday.common.session.SessionConstant;
import com.yongin.whichSunday.member.service.MemberService;
import com.yongin.whichSunday.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(LoginForm loginForm) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@RequestParam(defaultValue = "/") String redirectURI, @Validated LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        MemberVO checkedMember = memberService.checkLogin(loginForm);

        System.out.println("checkedMember = " + checkedMember);

        if (checkedMember == null) {
            bindingResult.reject("loginFail", "아이디 혹은 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(SessionConstant.LOGIN_MEMBER, checkedMember);
        }

        //로그인 체크
        return "redirect:" + redirectURI;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}

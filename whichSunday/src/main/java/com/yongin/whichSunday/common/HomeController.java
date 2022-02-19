package com.yongin.whichSunday.common;

import com.yongin.whichSunday.common.session.SessionConstant;
import com.yongin.whichSunday.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String homeLoginController(@SessionAttribute(name = SessionConstant.LOGIN_MEMBER, required = false) MemberVO loginMember, Model model) {
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}

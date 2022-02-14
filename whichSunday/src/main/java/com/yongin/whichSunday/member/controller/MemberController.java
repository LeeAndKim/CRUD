package com.yongin.whichSunday.member.controller;

import com.yongin.whichSunday.member.form.SignUpForm;
import com.yongin.whichSunday.member.service.MemberService;
import com.yongin.whichSunday.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/signUp")
    public String signUpForm(MemberVO memberVO) {
        return "login/signUpForm";
    }

    @PostMapping("/signUp")
    public String signUp(@Validated @ModelAttribute(name = "memberVO") SignUpForm signUpForm, BindingResult bindingResult) {

        System.out.println("signUpForm = " + signUpForm);

        if (bindingResult.hasErrors()) {
            log.info("bindingResult ={}", bindingResult);
            return "login/signUpForm";
        }

        MemberVO memberVO = new MemberVO();
        memberVO.setLoginId(signUpForm.getLoginId());
        memberVO.setPassword(signUpForm.getPassword());
        memberVO.setName(signUpForm.getName());
        memberVO.setAge(signUpForm.getAge());
        memberVO.setSex(signUpForm.getSex());
        memberVO.setAddress(signUpForm.getAddress());

        memberService.save(memberVO);

        return "redirect:/login";
    }

    @GetMapping("/members")
    public String memberInfo(Model model) {
        List<MemberVO> list = memberService.findAll();
        for (MemberVO memberVO : list) {
            System.out.println("memberVO = " + memberVO);
        }
        model.addAttribute("list", list);
        return "member/members";
    }

    @GetMapping("/member/{memberId}")
    public String findMemberInfo(@PathVariable Long memberId, Model model) {
        MemberVO member = memberService.findById(memberId);
        model.addAttribute("member", member);
        return "member/member";
    }

    @GetMapping("/updateInfo/{memberId}")
    public String updateMemberForm(@PathVariable Long memberId, Model model) {
        MemberVO memberInfo = memberService.findById(memberId);
        model.addAttribute("member", memberInfo);
        return "member/updateInfo";
    }

    @PostMapping("/updateInfo/{memberId}")
    public String updateMemberInfo(@PathVariable(name = "memberId") Long id, @Validated @ModelAttribute(name = "member") MemberVO memberVO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("bindingResult={} memberId={}", bindingResult, id);
            return "member/updateInfo";
        }
        MemberVO update = memberService.update(id, memberVO);
        redirectAttributes.addAttribute("memberId", id);
        return "redirect:/member/{memberId}";
    }

    @PostMapping("/delete/{memberId}")
    public String deleteMemberInfo(@PathVariable(name = "memberId") long id) {
        memberService.delete(id);
        return "redirect:/members";
    }
}

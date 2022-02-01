package com.yongin.whichSunday.member.controller;

import com.yongin.whichSunday.member.service.MemberService;
import com.yongin.whichSunday.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

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

    @PostMapping("/updateInfo")
    public String updateMemberInfo(Long id, MemberVO memberVO) {
        MemberVO update = memberService.update(id, memberVO);
        memberService.save(update);
        return "member/updateInfo";
    }

    @PostMapping("/delete")
    public String deleteMemberInfo(long id) {
        memberService.delete(id);
        return "member/members";
    }
}

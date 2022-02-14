package com.yongin.whichSunday.member.service;

import com.yongin.whichSunday.common.login.form.LoginForm;
import com.yongin.whichSunday.member.vo.MemberVO;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    MemberVO save(MemberVO memberVO);

    MemberVO findById(Long id);

    List<MemberVO> findAll ();

    MemberVO update(Long id, MemberVO memberVO);

    void delete(Long id);

    MemberVO checkLogin(LoginForm member);

}

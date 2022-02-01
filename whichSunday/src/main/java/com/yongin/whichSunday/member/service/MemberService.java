package com.yongin.whichSunday.member.service;

import com.yongin.whichSunday.member.vo.MemberVO;

import java.util.List;

public interface MemberService {

    MemberVO save(MemberVO memberVO);

    MemberVO findById(Long id);

    List<MemberVO> findAll ();

    MemberVO update(Long id, MemberVO memberVO);

    void delete(Long id);

}

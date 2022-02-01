package com.yongin.whichSunday.member.service.impl;

import com.yongin.whichSunday.member.service.MemberService;
import com.yongin.whichSunday.member.vo.MemberVO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {

    private static Map<Long, MemberVO> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public MemberVO save(MemberVO memberVO) {
        memberVO.setId(++sequence);
        memberVO.setDate(LocalDate.now().toString());
        store.put(memberVO.getId(), memberVO);
        return memberVO;
    }

    @Override
    public MemberVO findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<MemberVO> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public MemberVO update(Long id, MemberVO member) {
        MemberVO byMemberId = findById(id);
        byMemberId.setAge(member.getAge());
        byMemberId.setAddress(member.getAddress());
        byMemberId.setSex(member.getSex());
        byMemberId.setDate(member.getDate());

        return byMemberId;
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @PostConstruct
    void init() {
        MemberVO member1 = new MemberVO("홍길동1",11,"male", "yongin");
        MemberVO member2 = new MemberVO("홍길동2",12,"male", "yongin");
        MemberVO member3 = new MemberVO("홍길동3",13,"male", "yongin");
        save(member1);
        save(member2);
        save(member3);
    }
}

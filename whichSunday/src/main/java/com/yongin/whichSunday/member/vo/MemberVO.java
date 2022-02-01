package com.yongin.whichSunday.member.vo;

import lombok.Data;

@Data
public class MemberVO {
    private Long id;
    private String name;
    private int age;
    private String sex;
    private String address;
    private String date;

    public MemberVO() {
    }

    public MemberVO(String name, int age, String sex, String address) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }
}

package com.yongin.whichSunday.member.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SignUpForm {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotNull
    private int age;
    @NotEmpty
    private String sex;
    @NotEmpty
    private String address;

}

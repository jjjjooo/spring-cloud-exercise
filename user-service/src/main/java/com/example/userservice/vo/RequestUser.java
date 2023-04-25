package com.example.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {
    @NotNull(message = "이메일은 필수 입니다.")
    @Size(min = 2, message = "2글자 이상 적어주세요")
    @Email
    private String email;
    @NotNull(message = "이름은 필수 입니다.")
    @Size(min = 2, message = "2글자 이상 적어주세요")
    private String name;
    @NotNull(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "8글자 이상 적어주세요")
    private String pwd;
}

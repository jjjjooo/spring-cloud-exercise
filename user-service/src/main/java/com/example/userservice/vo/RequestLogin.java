package com.example.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestLogin {
    @NotNull(message = "이메일을 필수입니다.")
    @Size(min = 2, message = "최소 두글자")
    @Email
    private String email;

    @NotNull(message = "이메일을 필수입니다.")
    @Size(min = 8, message = "최소 8글자")
    private String pwd;
}

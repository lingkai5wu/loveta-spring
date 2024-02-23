package com.github.lingkai5wu.loveta.model.query;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthLoginQuery {
    /**
     * 手机号
     */
    @NotBlank
    @Pattern(regexp = "^1\\d{10}$")
    private String phone;

    /**
     * 验证码
     */
    @NotBlank
    @Pattern(regexp = "^\\d{6}$")
    private String smsCode;
}

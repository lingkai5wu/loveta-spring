package com.github.lingkai5wu.loveta.model.query;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthLoginQuery {
    /**
     * 手机号
     */
    @NotNull
    @Pattern(regexp = "^1\\d{10}$")
    private String phone;

    /**
     * 验证码
     */
    @NotNull
    @Pattern(regexp = "^\\d{6}$")
    private String smsCode;
}

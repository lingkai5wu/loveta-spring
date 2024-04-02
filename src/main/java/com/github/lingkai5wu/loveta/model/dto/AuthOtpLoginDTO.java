package com.github.lingkai5wu.loveta.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 验证码登录 数据传输对象
 *
 * @author lingkai5wu
 * @since 2023-12-25
 */
@Data
public class AuthOtpLoginDTO {

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
    private String otp;
}

package com.github.lingkai5wu.loveta.model.query;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthRegisterQuery extends AuthLoginQuery {
    /**
     * 验证码
     */
    @NotNull
    @Pattern(regexp = "^\\d{6}$")
    private String smsCode;
}

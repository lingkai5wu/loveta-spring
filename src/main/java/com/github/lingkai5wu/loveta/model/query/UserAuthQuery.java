package com.github.lingkai5wu.loveta.model.query;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserAuthQuery {
    /**
     * 手机号
     */
    @NotNull
    @Pattern(regexp = "^1\\d{10}$")
    private String phone;

    /**
     * 密码
     */
    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])\\S{6,20}$")
    private String password;
}

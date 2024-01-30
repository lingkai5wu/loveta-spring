package com.github.lingkai5wu.loveta.model.query;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserAuthQuery {
    /**
     * 手机号
     */
    @NotNull
    private String phone;

    /**
     * 密码
     */
    @NotNull
    private String password;
}

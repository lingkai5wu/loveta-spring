package com.github.lingkai5wu.loveta.model.query;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 6, max = 16)
    private String password;
}

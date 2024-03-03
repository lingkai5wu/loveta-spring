package com.github.lingkai5wu.loveta.model.vo.aggregate;

import com.github.lingkai5wu.loveta.model.vo.MenuVO;
import com.github.lingkai5wu.loveta.model.vo.UserVO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class RuntimeVO {
    /**
     * 当前用户
     */
    @NotNull
    private UserVO userVO;

    /**
     * 当前用户菜单
     */
    @NotNull
    private List<MenuVO> menuVOList;
}

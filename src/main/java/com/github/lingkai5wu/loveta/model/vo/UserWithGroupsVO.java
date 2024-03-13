package com.github.lingkai5wu.loveta.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserWithGroupsVO extends UserVO {
    private List<String> groups;
}

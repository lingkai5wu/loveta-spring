package com.github.lingkai5wu.loveta.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class BatchManyToManyDTO {

    /**
     * 需插入的目标实体ID
     */
    private List<Long> targetIdsToInsert;

    /**
     * 需删除的目标实体ID
     */
    private List<Long> targetIdsToDelete;
}

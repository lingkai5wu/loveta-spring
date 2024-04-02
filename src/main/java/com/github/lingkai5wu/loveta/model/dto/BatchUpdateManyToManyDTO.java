package com.github.lingkai5wu.loveta.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 批量修改多对多关系 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-03-18
 */
@Data
public class BatchUpdateManyToManyDTO<T> {

    /**
     * 需插入的目标实体ID
     */
    private List<T> targetIdsToInsert;

    /**
     * 需删除的目标实体ID
     */
    private List<T> targetIdsToDelete;
}

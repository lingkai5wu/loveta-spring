package com.github.lingkai5wu.loveta.model.dto;

import com.github.lingkai5wu.loveta.enums.OssImageProcessStyleEnum;
import lombok.Data;

/**
 * 生成对象存储获取文件 URL 数据传输对象
 *
 * @author lingkai5wu
 * @since 2024-04-11
 */
@Data
public class OssGetObjectUrlGenerateDTO {

    /**
     * 对象名称
     */
    private String objectName;

    /**
     * 图片处理样式
     */
    private OssImageProcessStyleEnum imageProcessStyle;
}

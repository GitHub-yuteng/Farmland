package com.harvest.core.domain.file;

import com.harvest.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Alodi
 * @Date: 2023/2/8 3:33 PM
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum OuterDataFormat implements IEnum<Integer> {

    /**
     * 外部数据格式
     */
    STRUCTURED_DATA     (1, "结构化数据[Json,Xml]"),
    REMOTE_FILE_URL     (2, "远程文件地址"),
    PDF_DATA            (3, "PDF文件数据流"),
    PICTURE_DATA        (4, "图片数据流"),
    HTML_DATA           (5, "HTML文件数据流"),
    PNG_DATA            (6, "PNG图片数据流");

    private final int key;
    private final String label;

    @Override
    public Integer getKey() {
        return this.key;
    }
}

package com.Riri.everything.core.model;

import lombok.Data;

/**
 * 检索的参数(条件)-->Condition
 */
@Data
public class Condition {
    //文件名称
    private String name;
    //文件类型
    private String fileType;
}

package com.Riri.everything.core.model;


/**
 * 检索的参数(条件)-->Condition
 */
public class Condition {
    //文件名称
    private String name;
    //文件类型
    private String fileType;
    //对查询结果个数做限制
    private Integer limit;
    /**
     * 检索结果的文件信息depth排序规则
     * 1.默认是true->asc
     * 2.false->desc
     */
    private Boolean orderByAsc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getOrderByAsc() {
        return orderByAsc;
    }

    public void setOrderByAsc(Boolean orderByAsc) {
        this.orderByAsc = orderByAsc;
    }
}

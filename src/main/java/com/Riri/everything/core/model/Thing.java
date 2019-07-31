package com.Riri.everything.core.model;

import lombok.Data;

//文件属性信息索引之后的记录Thing表示
public class Thing {
    /**
     * 文件名称(保留名称)
     * 如：D:/a/b/hello.txt -> hello.txt
     */
    private String name;
    /**
     * 文件路径
     */
    private String path;
    /**
     * 文件路径深度
     */
    private Integer depth;
    /**
     * 文件类型
     */
    private FileType fileType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", depth=" + depth +
                ", fileType=" + fileType +
                '}';
    }
}

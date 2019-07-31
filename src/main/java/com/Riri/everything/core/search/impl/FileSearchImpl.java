package com.Riri.everything.core.search.impl;

import com.Riri.everything.core.dao.FileIndexDao;
import com.Riri.everything.core.model.Condition;
import com.Riri.everything.core.model.Thing;
import com.Riri.everything.core.search.FileSearch;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
//从数据库中检索文件
//业务层
public class FileSearchImpl implements FileSearch {
    //数据源-->防止修改，立即赋值
    //final修饰变量赋值三种方法：1.直接赋值，2.构造方法，3.构造块
    private final FileIndexDao fileIndexDao;

    public FileSearchImpl(FileIndexDao fileIndexDao) {
        this.fileIndexDao = fileIndexDao;
    }

    @Override
    public List<Thing> search(Condition condition) {
        //数据库处理逻辑
        return this.fileIndexDao.search(condition);
    }
}

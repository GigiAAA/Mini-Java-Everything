package com.Riri.everything.core.dao;

import com.Riri.everything.core.model.Condition;
import com.Riri.everything.core.model.Thing;

import java.util.List;

/**
 * 业务层访问数据库的增删查改
 */
public interface FileIndexDao {
    /**
     * 根据condition条件进行数据库的插入数据Thing
     */
    void insert(Thing thing);
    /**
     * 根据condition条件进行数据库的检索
     */
    List<Thing> search(Condition condition);
}

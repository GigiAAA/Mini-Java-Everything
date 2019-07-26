package com.Riri.everything.core.search;

import com.Riri.everything.core.model.Condition;
import com.Riri.everything.core.model.Thing;

import java.util.List;

/**
 * 根据condition条件进行数据库的检索
 */
public interface FileSearch {
    List<Thing> search(Condition condition);
}

package com.Riri.everything.core.search;

import com.Riri.everything.core.dao.DataSourceFactory;
import com.Riri.everything.core.dao.impl.FileIndexImpl;
import com.Riri.everything.core.model.Condition;
import com.Riri.everything.core.model.Thing;
import com.Riri.everything.core.search.impl.FileSearchImpl;

import java.util.List;

/**
 * 根据condition条件进行数据库的检索
 */
public interface FileSearch {
    List<Thing> search(Condition condition);

    public static void main(String[] args) {
        //TODO
        FileSearch fileSearch=new FileSearchImpl(new FileIndexImpl(DataSourceFactory.dataSource()));
        List<Thing> list=fileSearch.search(new Condition());
        System.out.println(list);
    }
}

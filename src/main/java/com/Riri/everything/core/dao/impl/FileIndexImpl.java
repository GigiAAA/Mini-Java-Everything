package com.Riri.everything.core.dao.impl;

import com.Riri.everything.core.dao.DataSourceFactory;
import com.Riri.everything.core.dao.FileIndexDao;
import com.Riri.everything.core.model.Condition;
import com.Riri.everything.core.model.FileType;
import com.Riri.everything.core.model.Thing;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileIndexImpl implements FileIndexDao {
    private final DataSource dataSource;
    public FileIndexImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 插入数据
     * @param thing
     */
    @Override
    public void insert(Thing thing) {
        //连接
        Connection connection = null;
        //命令
        PreparedStatement statement=null;
        try {
            //1.获取数据库链接
            connection=dataSource.getConnection();
            //2.准备SQL语句
            //insert into file_index values(?,?,?,?)
            String sql="insert into file_index(name,path,depth,file_type) values(?,?,?,?)";
            //3.准备命令
            statement=connection.prepareStatement(sql);
            //4.设置参数
            statement.setString(1,thing.getName());
            statement.setString(2,thing.getPath());
            statement.setInt(3,thing.getDepth());
            //thing.getFileType().name()-->FileType.DOC-->存储到数据库时为DOC
            statement.setString(4,thing.getFileType().name());
            //5.执行命令
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            releaseResource(null,statement,connection);
        }
    }
    //检索并返回结果
    @Override
    public List<Thing> search(Condition condition) {
        List<Thing> things=new ArrayList<>();
        //连接
        Connection connection = null;
        //命令
        PreparedStatement statement=null;
        //查询结果
        ResultSet resultSet=null;
        try {
            //1.获取数据库链接
            connection=dataSource.getConnection();
            //2.准备SQL语句
            //文件名称模糊查询
            //name:模糊查询like   FileType:=  limit:limit offest  orderbyAsc:order by
            //当前StringBuilder存在虚拟机栈中，线程私有，不会线程共享，故不会出现线程竞争现象
            //若要成为属性时，则需要使用StringBuffer，因为属性线程共享，存在于方法区中，需线程安全
            StringBuilder sqlBuilder=new StringBuilder();
            //TODO
            sqlBuilder.append(" select name,path,depth,file_type from file_index");
            //name匹配原则：采用前后模糊
            sqlBuilder.append(" where ").append(" name like '%").append(condition.getName()).append("%' ");
            //文件类型查询
            if(condition.getFileType()!=null){
                //因数据库中枚举类型以大写存放，为了防止用户输入小写匹配不到，因此内部将类型转换为大写
                sqlBuilder.append(" and file_type = '").append(condition.getFileType().toUpperCase()).append("' ");
            }
            /**
             * 限制输出结果个数
             */
            sqlBuilder.append(" order by depth ").append(condition.getOrderByAsc() ? "asc":"desc");
            sqlBuilder.append(" limit ").append(condition.getLimit()).append(" offest 0 ");
            System.out.println(sqlBuilder.toString());
            //3.准备命令
            statement=connection.prepareStatement(sqlBuilder.toString());
            //4.执行命令
            resultSet=statement.executeQuery();
            //5.处理结果
            while (resultSet.next()){
                //数据库中行记录-->java中的String对象
                Thing thing=new Thing();
                thing.setName(resultSet.getString("name"));
                thing.setPath(resultSet.getString("path"));
                thing.setDepth(resultSet.getInt("depth"));
                String fileType=resultSet.getString("file_type");
                thing.setFileType(FileType.lookupByName(fileType));
                things.add(thing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            releaseResource(resultSet,statement,connection);
        }
        return things;
    }

    //解决内部代码大量重复问题：重构
    private void releaseResource(ResultSet resultSet,PreparedStatement statement,Connection connection){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        FileIndexDao fileIndexDao=new FileIndexImpl(DataSourceFactory.dataSource());
        Thing thing=new Thing();
        thing.setName("简历2.ppt");
        thing.setPath("E:\\a\\test\\简历2.ppt");
        thing.setDepth(3);
        thing.setFileType(FileType.DOC);

        fileIndexDao.insert(thing);
        Condition condition=new Condition();
        condition.setName("简历");
        condition.setLimit(2);
        condition.setOrderByAsc(true);
        List<Thing> things=fileIndexDao.search(condition);
        for(Thing t:things){
            System.out.println(t);
        }
    }
}

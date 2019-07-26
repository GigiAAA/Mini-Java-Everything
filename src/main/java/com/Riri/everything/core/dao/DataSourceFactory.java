package com.Riri.everything.core.dao;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 工厂类实现--以来Druid数据
 * 单例模式
 */
public class DataSourceFactory {
    private volatile static DruidDataSource dataSource;
    private DataSourceFactory(){}
    public static DataSource dataSource(){
        if(dataSource==null){
            synchronized (DataSourceFactory.class){
                if(dataSource==null){
                    //实例化
                    dataSource=new DruidDataSource();
                    //JDBC driver class
                    dataSource.setDriverClassName("org.h2.Driver");
                    //本地存储
                    //url,username,password
                    //采用的是H2的嵌入式数据库，数据库以本地文件的方式存储，只需要提供url即可
                    //获取当前工程路径
                    //JDBC规范中关于MySQL：jdbc:mysql://ip:port/databaseName
                    String workDir=System.getProperty("user.dir");
                    //JDBC规范中关于H2 jdbc:h2:filepath-->存储到本地文件
                    dataSource.setUrl("jdbc:h2:"+workDir+ File.separator+"mini_java_everything");
                }
            }
        }
        return dataSource;
    }
    //初始化数据库
    public static void initDatabase(){
        //1.获取数据源
        DataSource dataSource=DataSourceFactory.dataSource();
        //2.获取SQL语句
        //读取classpath路径下的文件
        try (InputStream in=DataSourceFactory.class.getClassLoader().getResourceAsStream("mini_java_everything.sql")){
            //若数据库脚本名称错误，则输入流为null
            if(in==null){
                throw new RuntimeException("无可读数据库脚本！");
            }
            StringBuilder sqlBuilder=new StringBuilder();
            try (BufferedReader reader=new BufferedReader(new InputStreamReader(in))){
                //line=null->末尾
                //若数据库脚本yi"--"开头，则表明为注释，不需要存储
                String line=null;
                while ((line=reader.readLine())!=null){
                    if(!line.startsWith("--")){
                        sqlBuilder.append(line);
                    }
                }
            }
            //将StringBuilder转换为可执行的sql语句
            String sql=sqlBuilder.toString();
            //JDBC
            //1.获取链接
            Connection connection=dataSource.getConnection();
            //2.创建命令
            PreparedStatement statement=connection.prepareStatement(sql);
            //3.执行sql语句
            statement.execute();
            //4.关闭连接
            statement.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

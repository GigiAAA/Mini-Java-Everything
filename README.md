这是一个很不错的Everything的Java版工具

将Terminal设置类似为Linux终端的方法：
File->setting->shell.path修改为你的路径/\Git\bin\bash.exe即可

执行jar包的方法为：
在Termial下找到jar包，使用以下命令行执行：
java -jar mini-java-everything-1.0.0.jar

首次将项目托管到git的流程：
1.在github中创建项目仓库；
2.在Terminal命令行工具中直接进行操作
    
    1）初始化项目：git init
        本地项目创建.gitignore，用于添加不加入版本管理的目录和文件
    2）将当前序列所有文件全部添加到暂存态:git add .
    3）查看文件状态:git status
    4）提交暂存态文件:git commit -m 搭建项目框架
    5）查看文件状态:git status-->此时文件已全部上传
        nothing to commit,working three clean
    6）查看提交记录：git log
    7）将本地仓库与远程仓库进行链接：git remote add origin 仓库地址
    8）查看仓库地址：git remote -v
    9）上传项目：git push origin master
3.刷新仓库，查看是否上传成功。    
  注意：之后进行代码更新时只需进行2)、4)、9)步即可

  IDEA中JAVA项目首次连接数据库步骤：
  1.Databese->点击+号->选择Data Source->选择MySQL


JDBC规范中关于H2 jdbc:h2:filepath-->存储到本地文件
JDBC规范中关于H2 jdbc:h2:~/filepath-->存储到当前用户的home目录
JDBC规范中关于H2 jdbc:h2://ip:port/databaseName-->存储到服务器
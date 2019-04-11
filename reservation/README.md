# SpringBoot+MyBatisPlus+MySQL+Maven
SpingBoot快速开发框架


## 技术支持
1. Apache-Maven  [参考地址](https://www.cnblogs.com/qbzf-Blog/p/6539161.html) [Maven3.x下载](http://maven.apache.org/download.cgi)
2. SpringBoot 2.1.2 
3. MybatisPlus   [参考地址](https://mp.baomidou.com/guide/)
4. MySQL 5.7     [下载地址](https://dev.mysql.com/downloads/mysql/5.7.html#downloads)
5. Tomcat 8.5    [下载地址](https://tomcat.apache.org/download-80.cgi#8.5.39)
6. Swagger2      [参考地址](https://blog.csdn.net/kkissyoulove/article/details/81706734)
7. lombok        [参考地址](https://blog.csdn.net/motui/article/details/79012846)

## 环境要求
* `Java8`及以上
* `SpringBoot1.2`及以上 + 基于`Servlet`的`Web`支持
* `MybatisPlus`
* `MySQL 5.7`
* `TomCat 8.5`

## 项目目录说明
src<br/>
└─ main  
│　　└─ java  
│　　　└─ com.zf  
│　　　　├─ common    //存放所有工具包常用拦截器类  
│　　　　├─ config    //存放所有配置类  
│　　　　└─ reservation       //存放所有业务层类  
│　　　　　└─ xxx       //业务名称　　<br/>
│　　　　　　├─ entity   //存放实体类　<br/>
│　　　　　　├─ mapper   //存放持久层mapper文件　<br/>
│　　　　　　├─ service  //存放业务逻辑接口类与实现类  <br/>
│　　　　　　├─ vo       //存放统计等相关pojo  
│　　　　　　└─ web      //存放控制层类  
│　　　　├─ util    //存放常用工具类  
│　　　　└─ Application.java    //SpringBoot 入口启动类  
│　　└─ resources  
│　　　　├─ application-dev.yml  //生产环境配置文件  
│　　　　├─ application-prod.yml //正式环境配置文件  
│　　　　├─ application.yml      //总配置文件  
│　　　　└─ logback.xml          //日志配置文件  
└─pom.xml //构建maven项目 主要配置文件  


## 构建说明

## FAQ
* 为何使用`MybatisPlus`？
    * MyBatis-Plus（简称 MP）是一个 MyBatis的增强工具，在MyBatis的基础上只做增强不做改变，为简化开发、提高效率

## TODO
* 支持`Spring4.1`及以上的版本

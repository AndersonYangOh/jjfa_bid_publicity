# 创新业务招投标宣传系统
## 一、基本配置
### 采取springboot+mybatis框架
### pom.xml配置
``` <dependency>```
    <groupId>org.mybatis.spring.boot</groupId>
*     <artifactId>mybatis-spring-boot-starter</artifactId>
*     <version>2.0.0</version>
* </dependency>
* <dependency>
*     <groupId>mysql</groupId>
*     <artifactId>mysql-connector-java</artifactId>
* </dependency>
### application.properties配置
* spring.datasource.url=jdbc:mysql://localhost:3306/weixinbid?useUnicode=true&characterEncoding=UTF-8
* spring.datasource.username=自定义
* spring.datasource.password=自定义
## 二、基本使用
* 创建@Controller
* 创建@Mapper
## 三、功能性描述
* 功能一：区域分类
![image](https://github.com/ChinaUnicomRI/jjfa_bid_publicity/blob/master/a.png)
* 功能二：省分数据列表

* 功能三：省分数据详情

* 可以关注：联通智汇公众号

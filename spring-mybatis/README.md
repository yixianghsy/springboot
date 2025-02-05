## 前言

> `MyBatis` 是一款优秀的持久层框架，它支持自定义 `SQL`、存储过程以及高级映射。MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。MyBatis 可以通过简单的 `XML` 或`注解`来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

本文通过 `Spring Boot + MyBatis` 实现一个用户管理的例子，来带大家入门 `MyBatis`。本教程适合小白入手，文中如有差错还请各位不吝赐教，大家一起学习，共同进步。

## 配置数据库

数据库采用的是 `MySQL`，首先提前安装好 `MySQL` 数据库。

### 创建数据库

数据库安装完毕后，在命令行中登录 `MySQL`：

```shell
$ mysql -u root -p
```
创建名字叫 `mybatisdemo` 的数据库：

```shell
$ create database mybatisdemo
```

进入 `mybatisdemo` 数据库：

```shell
$ use mybatisdemo
```

创建 `user` 表：

```sql
CREATE TABLE `user`
(
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(30) NOT NULL,
    `password` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
```
`user` 表包含三个字段：`id`、`user_name` 和 `password`，其中 `id` 是主键并且是自增的。

查看 `user` 表：

```shell
$ desc user;
```
结果如下：

```shell
+-----------+--------------+------+-----+---------+----------------+
| Field     | Type         | Null | Key | Default | Extra          |
+-----------+--------------+------+-----+---------+----------------+
| id        | int          | NO   | PRI | NULL    | auto_increment |
| user_name | varchar(30)  | NO   | UNI | NULL    |                |
| password  | varchar(255) | YES  |     | NULL    |                |
+-----------+--------------+------+-----+---------+----------------+
```

## 创建 Spring Boot 工程

### VSCode 创建 Spring Boot 工程

使用 `VSCode` 创建一个 `Spring Boot` 工程。通过 `command + shift + p` 快捷方式快速创建一个 `Spring Boot` 项目。

选择创建一个 `java` 项目：

![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122297.png)

选择项目类型为 `Spring Boot`：

![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122606.png)

选择创建一个 `Maven` 工程：

![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122473.png)

选择 `Spring Boot` 版本号：

![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122720.png)

选择工程的编程语言：

![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122603.png)

选择 `Group Id`，这里我填写 `com.sac`:

![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122347.png)

填写工程的 `Artifact Id`，这里我填写 demo:

![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122953.png)

选择打包类型：

![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122071.png)

选择 `java` 版本号：

![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122103.png)

选择需要安装的依赖包，这里暂时先跳过去：

![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122178.png)

到这里就成功初始化一个 `Spring Boot` 工程了，目录结构如下：

![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122267.png)

### 引入依赖

修改工程根目录下的 `pom.xml` 文件，引入相关依赖：

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!--数据库依赖-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <!--mybatis依赖-->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.1.1</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

- **spring-boot-starter-web**: 可以为Web开发提供支持，为我们提供了嵌入的 `Servlet` 容器以及 `Spring MVC` 的依赖，并为 `Spring MVC` 提供了大量自动配置。
- **mysql-connector-java**：数据库驱动包。
- **mybatis-spring-boot-starter**：连接 `Spring Boot` 和 `MyBatis`，构建基于 `Spring Boot` 的 `MyBatis` 应用程序。
- **lombok**：简化Java代码的工具包。

### 数据源配置

修改 `src/main/resources/application.yml` 文件进行数据源的配置：

```yml
server:
  port: 8090
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/logindemo?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2b8&allowPublicKeyRetrieval=true
    username: root
    password: root
mybatis:
  mapper-locations: classpath:mapping/*Mapper.xml
  type-aliases-package: com.sac.demo.entity
  configuration:
    map-underscore-to-camel-case: true
logging:
  level:
    com:
      sac:
        demo:
          mapper : debug
```
`application.yml` 配置文件中 `mybatis` 字段中定义的几个属性需要关注一下。

- **mapper-locations**：指定 `MyBatis` 的 `XML` 映射文件的位置，`mapping/*Mapper.xml` 表示 `MyBatis` 会去 `resources/mapping` 目录下查找所有以 `Mapper` 结尾的 `xml` 文件，作为映射文件。
- **type-aliases-package**：扫描实体类的位置，在此处指明扫描实体类的包，在 `mapper.xml` 中就可以不写实体类的全路径名。
- **map-underscore-to-camel-case**：通常数据库列使用大写字母组成的单词命名，单词间用下划线分隔，而 `Java` 属性一般遵循驼峰命名法约定。为了在这两种命名方式之间启用自动映射，需要将 `mapUnderscoreToCamelCase` 设置为 `true`。

### 创建实体类

根据我们之前创建的数据库 `user` 表，在 `src/main/java/com/sac/demo` 目录下创建 `entity` 目录，并在 `entity` 目录下创建对应的实体类 `User.java`，代码如下：

```java
package com.sac.demo.entity;

import lombok.Data;

@Data
public class User {
  private long id;

  private String userName;

  private String password;
}
```

这里我用了 `lombok` 包提供的 Data 注解，它会自动帮我们生成 `getter/setter` 和 `toString` 等方法，可以简化我们的 `java` 的代码。

注意一个细节，实体类里定义的 `userName` 字段是驼峰式的，而数据库里的字段user_name是带下划线的，这里能自动映射靠的就是我们之前在 `application.yml` 中开启的 `map-underscore-to-camel-case` 配置项。

### 创建 mapper 接口

`MyBatis` 中提供了两种方式来实现 `SQL` 语句映射，一种是通过 `XML` 来定义语句，还有一种是通过注解的方式，注解的方式更加简洁、方便，但是不如 `XML` 来的功能强大、直观，这里由于文章篇幅有限，重点介绍 `XML` 的形式来实现 `SQL` 语句映射。

首先定义一个 `mapper` 接口。

在 `src/main/java/com/sac/demo` 目录下创建 `mapper` 目录，在 `mapper` 目录中新建 `UserMapper.java` 接口文件，内容如下：

```java
package com.sac.demo.mapper;

import com.sac.demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
  int insertUser(User user);

  User getByUserNameAndPassword(User user);
}
```

为了能在 `Spring Boot` 启动的时候找到我们定义的 `mapper` 接口文件，还需要在启动类中通过`@MapperScan("com.sac.demo.mapper")` 注解指定 `mapper` 文件的扫描的路径：

```java
package com.sac.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.sac.demo.mapper")
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
```

### 创建 mapper XML

上节中创建的 `mapper` 接口文件还需要创建一个 `mapper XML` 文件与之对应，`mapper XML` 文件中主要定义了 `SQL` 语句。

在 `resources/mapping` 目录下创建一个 `UserMapper.xml` 文件，由于之前我们在 `application.yml` 中通过 `mapper-locations` 属性已经指定了映射文件的查找路径，因此 MyBatis 会自动扫描此指定包的所有 `mapper` 并创建实现类。

`UserMapper.xml` 内容如下：

```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.sac.demo.mapper.UserMapper">
    <insert id="insertUser" parameterType="User" useGeneratedKeys="true" keyProperty="id">
        insert into user (user_name,password) values (#{userName},#{password})
    </insert>

    <select id="getByUserNameAndPassword" parameterType="User" resultType="User">
        select *
        from user
        where user_name = #{userName}
          and password = #{password}
    </select>
</mapper>
```

在开发 `mapper` 文件过程中需要注意以下几点：

- `mapper` 映射文件的 `namespace` 必须要和 `mapper` 接口的完全限定名保持一致。
- `mapper` 映射文件中 `statement` 的 `id` 必须与 `mapper` 接口中的方法的方法名保持一致。
- `mapper` 映射文件中 `statement` 的 `parameterType` 指定的类型必须与 `mapper` 接口中方法的参数类型保持一致。
- `mapper` 映射文件中 `statement` 的 `resultType` 指定的类型必须与 `mapper` 接口中方法的返回值类型保持一致。如果在 `application.yml` 文件中的 `type-aliases-package` 指定了扫描实体类的位置，就可以省略类的具体路径而直接写类名。
- `insert` 语句中，因为 `user` 表的 `id` 是自增的，那么，如果在 `SQL` 中不传 `id`，但希望获取插入后的主键，就可以设置 `useGeneratedKeys` 属性。

`MyBatis` XML 映射器的具体用法可以参考 [MyBatis 文档](https://mybatis.org/mybatis-3/zh/sqlmap-xml.html#select)。

### 创建 service

在 `src/main/java/com/sac/demo` 目录下创建 `service` 目录，在 `service` 目录中新建 `UserService.java` 文件，内容如下：

```java
package com.sac.demo.service;

import org.springframework.stereotype.Service;
import com.sac.demo.mapper.UserMapper;
import com.sac.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
  @Autowired
  UserMapper userMapper;

  public int insertUser(User user) {
    return userMapper.insertUser(user);
  }

  public User getByUserNameAndPassword(User user) {
    return userMapper.getByUserNameAndPassword(user);
  }
}
```

`service` 中通过 `@Autowired` 注解注入 `userMapper`，`service` 就可以通过 `userMapper` 中定义的数据库方法来访问数据库。

### 创建 controller

在 `src/main/java/com/sac/demo` 目录下创建 `controller` 目录，在 `controller` 目录中新建 `UserController.java` 文件，内容如下：

```java
package com.sac.demo.controller;
import com.sac.demo.entity.User;
import com.sac.demo.service.UserService;
import org.apache.coyote.http11.filters.VoidInputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("user/insert")
  public Response insertUser(@RequestBody User user) {
      int result = userService.insertUser(user);
      return Response.success(result);
  }

  @PostMapping("user/getByUserNameAndPassword")
  public Response getByUserNameAndPassword(@RequestBody User user) {
      User result = userService.getByUserNameAndPassword(user);
      return Response.success(result);
  }
}
```

通过 @PostMapping 注解定义了两个 POST 请求，路由 `user/insert` 表示新增一条记录，路由 `user/getByUserNameAndPassword` 表示校验用户名和密码是否正确。

但是仔细看现在的 `controller` 还存在两个问题。

接口返回给前端的格式应当统一，这样才方便前端统一处理所有接口返回值。

接口异常的情况没有处理，应该将后端接口的异常统一处理后以错误码和错误信息的形式抛给前端，方便前端进行错误提示。

针对上面的两个问题，在 `entity` 目录中定义一个 `Response` 实体类来统一处理返回结果。

```java
package com.sac.demo.entity;

public class Response {
  private int code;

  private String msg;

  private Object data;

  public Response() {
    super();
  }

  public Response(int code, String msg, Object data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public static Response success(Object data) {
    Response item = new Response(200, "success", data);
    return item;
  }

  public static Response failure(int errCode, String errorMessage) {
    Response item = new Response(errCode, errorMessage, null);
    return item;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
```
`Response` 实体提供了两个静态方法 `success` 和 `failure` 分别提供给调用成功和调用失败的时候使用。接口返回对象中包含 `code`、`msg` 和 `data` 三个属性。

优化后的 controller 如下：

```java
@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("user/insert")
  public Response insertUser(@RequestBody User user) {
    try {
      int result = userService.insertUser(user);
      return Response.success(result);
    } catch(Exception e) {
      return Response.failure(500, "服务器异常");
    }
  }

  @PostMapping("user/getByUserNameAndPassword")
  public Response getByUserNameAndPassword(@RequestBody User user) {
    try {
      User result = userService.getByUserNameAndPassword(user);
      return Response.success(result);
    } catch(Exception e) {
      return Response.failure(500, "服务器异常");
    }
  }
}
```
\## 接口测试

启动 Spring Boot 后，通过 http://localhost:8090 地址就可以访问后台服务了。

我们通过 Postman 来分别测试下”新增“和”登录“两个接口。

先测试"新增"接口：

![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122263.png)

数据插入成功，接口的返回值格式也符合我们的预期。

再测试”登录“接口：

![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122526.png)

可以看到，如果用户名和密码验证成功就认为登录成功，接口会返回当前查询到的记录。

最后再测试一下异常情况，假设我们插入数据库的字段有误，接口直接就会返回错误结果。


![image.png](http://hsy.sylianxizhuanyong.cn:9001/blog/2025/02/02/202502021122627.png)

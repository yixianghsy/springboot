> 知识点涵盖：代码自动生成、主键自增（雪花算法）、分页、自动填充字段、LocalDateTime 序列化配置、druid数据源配置、SQL监控页面、逻辑删除、事务管理、多环境配置等等。
>
> 1、 可以无缝内嵌进项目，也可以保存下来，方便下次使用。
>
> 2、代码中带有很多注解，为方便对MybatisPlus了解不深的同学，也能够快速看懂。👨‍💻or🛌
>
> 3、不行的话一步一步复制，也是可以运行起来的，慢慢看更好。

![在这里插入图片描述](https://img-blog.csdnimg.cn/f4d37d808b4b41c3a0bc6380e7ef9319.JPG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)
`地点`:湖南邵阳
`作者`:喜
@[TOC](SpringBoot集成MybatisPlus 涵盖了目前流行的知识点！！)

## 一、前言

最近在写一个关于SpringBoot 系列的文章，在逐渐整理相关的知识，打算慢慢写出来，作为了一个工具，随拿随用。👨‍⚖️

本文写的是SpringBoot-MybatisPlus，完整项目结构如下图：

![在这里插入图片描述](https://img-blog.csdnimg.cn/4e2612dff4cc436892b311964fbdc064.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


下面将会一一道来，有任何不懂的地方，都可以私信或留言评论，会及时给出回复。

若有写的不对或不妥的地方，请您指教！！！非常感谢。🤶

## 二、基础环境搭建

#### 2.1、数据库环境搭建：

```sql
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passwrod` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deleted` int(1) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `tb_user` VALUES ('1', '123456789', '123456', 0, '2021-07-23 14:32:46', '2021-07-24 10:51:11');
INSERT INTO `tb_user` VALUES ('2', '宁在春', 'qwerasd', 0, '2021-07-23 15:02:02', '2021-07-23 15:49:55');
```

#### 2.2、maven导入依赖：

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.2</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.23</version>
    </dependency>
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.4.1</version>
    </dependency>

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.2.6</version>
    </dependency>

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.72</version>
    </dependency>

    <!--start mybatis-plus 逆向工程 自动生成代码-->
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-generator</artifactId>
        <version>3.4.1</version>
    </dependency>
    <!--逆向工程中的模板引擎-->
    <dependency>
        <groupId>org.apache.velocity</groupId>
        <artifactId>velocity-engine-core</artifactId>
        <version>2.2</version>
    </dependency>
    <!--end-->

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

#### 2.3、yml配置文件

多配置😎

**1、application.yml**

```yml
spring:
  profiles:
    active: prod
```

**2、application-prod.yaml**

```yml
server:
  port: 8081
  worker-id: 1
  data-center-id: 2
spring:
  application:
    name: springboot-mybatis-plus
  # 数据源配置
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    # 阿里的数据库连接池
    druid:
      username: root
      password: 123456
      url: jdbc:mysql://localhost:3306/commons_utils?serverTimezone=UTC&useSSL=false&characterEncoding=utf8&serverTimezone=GMT
      # 初使化连接数(向数据库要五个连接)
      initial-size: 5
      # 最小连接数(常住10个连接)
      min-idle: 10
      # 最大连接数(最多获得10个连接，多到10个数据库将进入一个阻塞状态，等待其他连接释放)
      max-active: 20
      # 获取连接最长等待时间，单位毫秒
      max-wait: 10000
      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
      timeBetweenEvictionRunsMillis: 60000
      # 配置一个连接在池中最小生存的时间，单位是毫秒
      minEvictableIdleTimeMillis: 300000
      # 配置一个连接在池中最大生存的时间，单位是毫秒
      maxEvictableIdleTimeMillis: 900000
      # 配置检测连接是否有效
      validationQuery: SELECT 1 FROM DUAL
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
      #配置监控页面
      stat-view-servlet:
        enabled: true
        url-pattern: /druid/*
        login-username: admin
        login-password: admin
      filter:
        stat:
          enabled: true
          log-slow-sql: true
          # 慢SQL记录
          slow-sql-millis: 1000
          merge-sql: true
        wall:
          config:
            multi-statement-allow: true
        slf4j:
          enabled: true
      keep-alive: true
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
mybatis-plus:
  configuration:
    cache-enabled: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl #开启sql日志
  mapper-locations: classpath:/mapper/**/*Mapper.xml #mapper.xml映射
  global-config:
   db-config:
    logic-delete-field: flag  # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
    logic-delete-value: 1 # 逻辑已删除值(默认为 1)
    logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
#logging: #日志打印、sql语句打印
#  level:
#    root: info
#    com.crush.mybatispllus.mapper: debug
```

#### 2.4、mybatis-plus 逆向工程生成代码

##### 2.4.1、初始化项目结构：

![在这里插入图片描述](https://img-blog.csdnimg.cn/10aea230df6f4794baf5df1da82f3155.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


##### 2.4.2、mybatisplus逆向共程代码

```java
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("crush");
        gc.setOpen(false);
        gc.setIdType(IdType.AUTO);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/commons_utils?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.crush");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        // 公共父类
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);

//        strategy.setTablePrefix(pc.getModuleName() + "_");
        //去掉 表前缀 "tb_"  需求变化的话 可以提取出来
        strategy.setTablePrefix("tb"+"_");

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

}
```

##### 2.4.3、启动与示例

模块名 就是在已建立好的com.crush包下建一个新包 以这个为命名。==注意哈：我项目中的包实际为mybatisplus，这是写文章时实时测试的。==😜

![在这里插入图片描述](https://img-blog.csdnimg.cn/b9bb8797c3d44e07941a840ef0e04fbd.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


![在这里插入图片描述](https://img-blog.csdnimg.cn/c6cb2a8a7c444da68bb9d9524ae53d30.png#pic_center)


##### 2.4.4、生成后的项目结构

![在这里插入图片描述](https://img-blog.csdnimg.cn/4ef75e2cfd0045c3acfd7bc17c0ee4d6.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


对了，记得写一个启动类兄弟们，如果直接SpringBoot 项目请忽略。

##### 2.4.5、生成代码查看

基本注解都会给带上，但是还是有一些需要手动完善一下的，还有很多可以玩的，我还没有全部玩完👩‍🚀👩‍🚀。这里不多扯。😚

![在这里插入图片描述](https://img-blog.csdnimg.cn/dd59c8f9d45b4474a1c6c424544938ef.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


`补充：` 因为实体类上需要完善一些注解。所以将完整的实体类在此处写出来了。

```java
@EqualsAndHashCode(callSuper = false)
//@Accessors 链式书写 或 @AllArgsConstructor 全参构造
@Accessors(chain = true)
@TableName("tb_user")
@KeySequence("mybatisKeyGenerator")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private String id;
    
    private String username;

    private String passwrod;
    /**
     * 逻辑删除字段
     */
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
```

到此基本环境已基本搭建完毕，加一个启动类、配置类即可开始测试。(后面都有的，莫慌👨‍💻👨‍💻)

## 三、配置类讲解

#### 3.1、MybatisPlusConfig

```java
package com.crush.mybatisplus.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @EnableTransactionManagement :开启事务
 * @MapperScan() 扫描包
 * @Author: crush
 * @Date: 2021-07-23 14:14
 * version 1.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.crush.mybatisplus.mapper")
public class MybatisPlusConfig {

    /**
     * 分页 插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 注册乐观锁 插件
        return mybatisPlusInterceptor;
    }

    /**
     * 配置数据源 druid
     */
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid")
    public DruidDataSource druidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
    
}
```

写到这里即可以直接开测了，下面更多的是细节方面的处理。（我写的测试在文末，知识点内的测试都有）😶

#### 3.2、自动填充字段

```java
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 填充创建和修改时间
 * @Author: crush
 * @Date: 2021-07-23 14:14
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info(" 插入填充 start insert fill ....");
        // 我去看了一下介绍,其实这里是个通用填充，并不局限于填充时间哈 
        this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("修改填充 start update fill ....");
        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }
}
```

`注意`: 需要在填充的字段上加上注解。🤗

```java
/**
* 创建时间
*/
@TableField(fill = FieldFill.INSERT)
private LocalDateTime createTime;

/**
* 修改时间
*/
@TableField(fill = FieldFill.INSERT_UPDATE)
private LocalDateTime updateTime;
```

#### 3.3、主键自动生成

```java
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 主键自动生成
 * @Author: crush
 * @Date: 2021-07-23 14:14
 */
@Slf4j
@Component
public class MybatisKeyGenerator implements IKeyGenerator {
 
	@Value("${server.worker-id}")
	private Integer workerId;
 
	@Value("${server.data-center-id}")
	private Integer dataCenterId;
 
	@Override
	public String executeSql(String incrementerName) {
		log.info("mybatis plus keyGenerator: " + incrementerName + "(" + workerId + "," + dataCenterId + ")");
		long uid = new SnowflakeIdWorker(workerId, dataCenterId).nextId();
		return "select " + uid + " from dual";
	}
}
```

`注意`:   id 字段上需要有这个注解哈。因为我们是自定义了id  的生成，并不是使用的哈。😗🤑

```java
@TableId(type = IdType.INPUT) // 如果使用默认的话 @TableId(type = IdType.AUTO)
private String id;
```

另外，使用自定义的还需 在实体类上 加上一个`@KeySequence("mybatisKeyGenerator")`注解。  mybatisKeyGenerator是bean注入时的名称哈。
即

```java
@KeySequence("mybatisKeyGenerator")
public class User implements Serializable {}
```


👨‍💻👨‍💻

> ==SnowflakeIdWorker== 是mybatisplus中官方文档中说的 Id自增用的雪花算法。
>
> 简介：SnowFlake是Twitter公司采用的一种算法，目的是在分布式系统中**产生全局唯一且趋势递增的ID。**
>
> 直接Copy就好，这里只是简单使用，没有详讲。好奇的朋友可以去查一查相关讲雪花算法的文。😚

```java
/**
 * 0 | 0001100 10100010 10111110 10001001 01011100 00 | 10001 | 00001 | 0000 00000000
 * <p>
 * 0          | 0001100 10100010 10111110 10001001 01011100 00 |    10001   |  00001  | 0000 00000000
 * 0          |       timestamp                                |datacenterId| workerId |    sequence
 * 正数(占位) |       时间戳二进制                             | 数据中心ID | 机器ID | 同一机房同一机器相同时间产生的序列
 *
 * @author crush
 */
public class SnowflakeIdWorker
{

    /**
     * 数据中心(机房) id
     */
    private long datacenterId;
    /**
     * 机器ID
     */
    private long workerId;
    /**
     *  同一时间的序列
     */
    private long sequence;

    /**
     * 构造方法
     *
     * @param workerId     工作ID(机器ID)
     * @param datacenterId 数据中心ID(机房ID)
     *                     sequence 从0开始
     */
    public SnowflakeIdWorker(long workerId, long datacenterId)
    {
        this(workerId, datacenterId, 0);
    }

    /**
     * 构造方法
     *
     * @param workerId     工作ID(机器ID)
     * @param datacenterId 数据中心ID(机房ID)
     * @param sequence     序列号
     */
    public SnowflakeIdWorker(long workerId, long datacenterId, long sequence)
    {
        // sanity check for workerId and datacenterId
        // 机房id和机器id不能超过32，不能小于0
        if (workerId > maxWorkerId || workerId < 0)
        {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0)
        {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    /**
     *  开始的时间戳(2015-01-01)
     */
    private long twepoch = 1420041600000L;

    /**
     * 数据中心(可以理解为机房)的ID所占的位数 5个bite 最大:11111(2进制)--> 31(10进制)
     */
    private long datacenterIdBits = 5L;

    /**
     *  机器ID所占的位数 5个bit 最大:11111(2进制)--> 31(10进制)
     */
    private long workerIdBits = 5L;

    /**
     * 这个是二进制运算，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
     * 11111(2进制)--> 31(10进制)
     */
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     *  5 bit最多只能有31个数字，机房id最多只能是32以内
     *  同上
     */
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     *  同一时间的序列所占的位数 12个bit 111111111111 = 4095  最多就是同一毫秒生成4096个
     */
    private long sequenceBits = 12L;

    // workerId的偏移量
    // 0 | 0001100 10100010 10111110 10001001 01011100 00 |    10001   |  00001  | 0000 00000000
    // 0 |       timestamp                                |datacenterId| workerId |    sequence
    //                                                                  << sequenceBits
    private long workerIdShift = sequenceBits;

    // datacenterId的偏移量
    // 0 | 0001100 10100010 10111110 10001001 01011100 00 |    10001   |  00001  | 0000 00000000
    // 0 |       timestamp                                |datacenterId| workerId |    sequence
    //                                                     << workerIdBits + sequenceBits
    private long datacenterIdShift = sequenceBits + workerIdBits;

    // timestampLeft的偏移量
    // 0 | 0001100 10100010 10111110 10001001 01011100 00 |    10001   |  00001  | 0000 00000000
    // 0 |       timestamp                                |datacenterId| workerId |    sequence
    //    <<  sequenceBits + workerIdBits + sequenceBits
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    /**
     *  序列号掩码 4095 (0b111111111111=0xfff=4095)
     *     // 用于序号的与运算，保证序号最大值在0-4095之间
     */
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 最近一次获取id的时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 获取工作ID(机器ID)
     *
     * @return
     */
    public long getWorkerId()
    {
        return workerId;
    }

    /**
     * 获取数据中心ID(机房ID)
     *
     * @return
     */
    public long getDatacenterId()
    {
        return datacenterId;
    }

    /**
     * 获取最新一次获取的时间戳
     *
     * @return
     */
    public long getLastTimestamp()
    {
        return lastTimestamp;
    }

    /**
     * 获取下一个随机的ID
     *
     * @return
     */
    public synchronized long nextId()
    {
        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();

        if (timestamp < lastTimestamp)
        {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        // 判断本次的时间和前一次的时间是否一样
        if (lastTimestamp == timestamp)
        {
            // 如果一样说明是同一时间获取多次
            // 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            sequence = (sequence + 1) & sequenceMask;

            // 如果与运算得到了0 说明sequence序列已经大于看4095
            // 如4096 = 1000000000000
            //   1000000000000
            // &  111111111111
            // =  000000000000
            // =  0
            if (sequence == 0)
            {
                // 调用到下一个时间戳的方法
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        else
        {
            // 如果是当前时间的第一次获取，那么就置为0
            sequence = 0;
        }

        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;

        // 按上面的偏移量进行左移动
        // 首位的0可以忽略
        // 时间戳 << 22 |
        // datacenterId << 17 |
        // workerId << 12 |
        // sequence
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    /**
     * 切到下一个时间戳
     * 作用是，当如果出现同一个时间戳内，获取的次数超过了4095
     * 死循环至下一个时间戳，避免冲突
     *
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp)
    {
        // 获取最新的时间戳
        long timestamp = timeGen();
        // 如果发现最新的时间戳小于或者等于序列号已经超4095的那个时间戳
        while (timestamp <= lastTimestamp)
        {
            // 如果是小于或者等于的   那我们就继续死循环获取下一个时间戳
            // 指导切换到了下一个时间戳
            timestamp = timeGen();
        }
        // 返回新的时间戳
        return timestamp;
    }

    /**
     * 获取当前时间戳
     *
     * @return 返回时间戳的毫秒数
     */
    private long timeGen()
    {
        return System.currentTimeMillis();
    }

    //---------------测试---------------
    public static void main(String[] args)
    {
        SnowflakeIdWorker worker = new SnowflakeIdWorker(1, 1);
        long timer = System.currentTimeMillis();
        for (int i = 0 ; i < 260000 ; i++)
        {
            worker.nextId();
        }
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() - timer);
    }

}
```

~~(太占我篇幅了。👴)~~

#### 3.4、LocalDateTimeSerializerConfig（LocalDateTime序列化）

> 简单介绍：此类的作用就是将LocalDateTime 进行格式化的配置，另外注册了两个类型转换器。😀

```java
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime 序列化配置
 * @Author: crush
 * @Date: 2021-07-23 14:14
 */
@Configuration
public class LocalDateTimeSerializerConfig {


    @Value("${spring.jackson.date-format}")
    private String DATE_TIME_PATTERN;

    @Value("${spring.jackson.date-format}")
    private  String DATE_PATTERN ;

    /**
     * string转localdate
     */
    @Bean
    public Converter<String, LocalDate> localDateConverter() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                if (source.trim().length() == 0) {
                    return null;
                }
                try {
                    return LocalDate.parse(source);
                } catch (Exception e) {
                    return LocalDate.parse(source, DateTimeFormatter.ofPattern(DATE_PATTERN));
                }
            }
        };
    }

    /**
     * string转localdatetime
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                if (source.trim().length() == 0) {
                    return null;
                }
                // 先尝试ISO格式: 2019-07-15T16:00:00
                try {
                    return LocalDateTime.parse(source);
                } catch (Exception e) {
                    return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
                }
            }
        };
    }

    /**
     * 统一配置 LocalDateTime 格式化 直接规定LocalDateTime的格式。
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        JavaTimeModule module = new JavaTimeModule();
        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        return builder -> {
            builder.simpleDateFormat(DATE_TIME_PATTERN);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_PATTERN)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
            builder.modules(module);
        };
    }
}

```

终于整到这一步啦，剩下的就只剩测试和实用啦。兄弟，都到这里啦丫，看完吧。

## 四、测试

```java
@SpringBootTestpublic class UserTest {    @Autowired    IUserService userService;}
```

#### 4.1、增加：

```java
@Testpublic void testInsert(){    User crush = new User().setUsername("qqqq").setPasswrod("987456");    boolean b = userService.save(crush);    System.out.println(b);}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/a0b279c3a99c480ca5cf200be8600000.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


从结果，可以看到我们已经成功啦。

#### 4.2、删除：

`补充`：我们在这里的删除，实际上是逻辑删除，数据库中数据仍在，并非物理删除，这是为了防止误删而设置的。也是一种数据的保留方式。

```java
@Testpublic void testDelete(){    QueryWrapper<User> wrapper = new QueryWrapper<>();    // 将sql 语句中众多的条件 换成了代码 这里没有细讲，之后会出文章 讲这个Wrapper。    // 此处意思是  拼成 sql 语句即为  username=qqqq (注：是更在where子句后)     wrapper.eq("username","qqqq");    boolean remove = userService.remove(wrapper);    System.out.println(remove);}
```

此处执行的实质SQL语句也为修改语句，并非delete语句。

![在这里插入图片描述](https://img-blog.csdnimg.cn/85a1adbae48f4cabaa7281c44af296a5.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


#### 4.3、查找：（分页查找）

```java
@Testpublic void  selectList(){    List<User> list = tbUserService.list(); // 查询全部}
```

```java
@Testpublic void testPage(){    // 第一个参数 当前页码 第二个参数是 每一页的大小      // 这里的 1,5 说的是查询第一页 ,每页展示5条    Page<User> page = new Page<>(1,5);    Page<User> tbUserPage = userService.page(page);    // 传给前台时，并不需要这么读取，这里是为了展示  getRecords() 是获取查询到的记录。    List<User> records = tbUserPage.getRecords();    records.forEach(System.out::println);}
```

从结果可以看出是没有任何问题的哈。

![在这里插入图片描述](https://img-blog.csdnimg.cn/9a063925ac22408eba66ccba5f4376c5.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


#### 4.4、修改：

```java
@Testpublic void testUpdate(){    UpdateWrapper<User> wrapper = new UpdateWrapper<>();    // 将sql 语句中众多的条件 换成了代码 这里没有细讲，之后会出文章 讲这个Wrapper。    // 此处意思是  拼成 sql 语句即为  username=qqqq (注：是更在where子句后)    wrapper.eq("id",1);    User crush = new User().setUsername("宁在春").setPasswrod("123456");    userService.update(crush,wrapper);}
```



![在这里插入图片描述](https://img-blog.csdnimg.cn/cc7d2dda849b47de8e54bf21744d1f41.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)




#### 4.5、事务回滚：

```java
/*** 事务回滚*/@Transactional@Testpublic void testWork(){    //start-------- delete from tb_user where username=宁在春;    QueryWrapper<User> wrapper = new QueryWrapper<>();    wrapper.eq("username","宁在春");    tbUserService.remove(wrapper);    //end    //start-------- update set username="我是新手" where id99999=1;     UpdateWrapper<User> wrapper1 = new UpdateWrapper<>();    // 在这里我故意将字段写错 那么这条SQL 语句 肯定会报错。     wrapper1.eq("id99999",1);    User crush = new User().setUsername("我是新手");    tbUserService.update(crush,wrapper1);    //end---------- 这条sql 语句 ，我们知道肯定是不会生效的，那么上面生效的是否回回滚呢？}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/484bccf328ee45b488ec31810229da5b.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


#### 4.6、druid 监控页面

> druid 配置方式，我并没有采取常见的bean注入方式，而是写在了yml配置文件中。用bean也有好处，就是账号密码等等可以动态。

![在这里插入图片描述](https://img-blog.csdnimg.cn/062abcb3a28642f58397c9b65288f286.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)


为了方便测试，我在controller层中稍微写了点，并补充了启动类哈。

```java
/** * @author crush * @since 2021-07-23 */@RestControllerpublic class UserController {    private final IUserService tbUserService;    public UserController(IUserService tbUserService) {        this.tbUserService = tbUserService;    }    @RequestMapping("/list")    public List<User> list(){       return tbUserService.list();    }}
```

```java
@Slf4j@SpringBootApplicationpublic class MyBatisPlus {    public static void main(String[] args) {        SpringApplication.run(MyBatisPlus.class);        log.info("druid 监控页面：localhost:8081/druid");    }}
```

druid 监控页面链接：localhost:8081/druid 会直接去到登录页面，账号密码就是配置好的admin。

**测试：**

跑一下查询全部的接口，然后在sql监控页面已经可以看到sql信息啦。点进去的话，能看到详细信息。

![在这里插入图片描述](https://img-blog.csdnimg.cn/291f922586454d00af9d27be2f0ce076.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTgyMTgxMQ==,size_16,color_FFFFFF,t_70#pic_center)




## 五、自言自语

不知道你有没有收获，如果能够帮助到你，就让我知道吧，让我享受一下分享知识的快乐吧。

如果存有疑惑，就私信或留言吧，定会及时回复的。

如有不足之处，也请大家能够及时指出！！👨‍💻👨‍💻

今天就到这里啦，明天接着更mybatis-plus结合redis做缓存哈。（使用缓存组件的方式）

`共勉`or`🛌` 
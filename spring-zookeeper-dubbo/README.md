> 友情提醒：本文可能是你看过的 ~~最啰嗦、最无聊~~ 最详细的一篇项目搭建文章 
> 如你对本文所涉及的技术基础有较大自信，建议从项目搭载开始阅读

> ***同时本文主要是进行简单的整合，注重的逻辑思维的梳理，而没有复杂的编码案例，仅适合入门引导，如你不是初学者，不建议你进行阅读***

## 谁适合阅读本文？
1、具有Java、Spring、Maven、springboot较好的基础（如果有一个技术一看完全不知道是什么，建议关掉本文/点击下面链接，去先学习完相关技术再来阅读本文）
&emsp;&emsp;[Java教程](https://www.runoob.com/java/java-tutorial.html)
&emsp;&emsp;[Spring教程](http://c.biancheng.net/spring/what-is-spring.html)
&emsp;&emsp;[Maven教程](https://www.runoob.com/maven/maven-tutorial.html)
&emsp;&emsp;[SpringBoot教程](http://c.biancheng.net/spring_boot/)
2、对dubbo、zookeeper有一个基本的认识
&emsp;&emsp;[Dubbo 2.x 官方文档](https://dubbo.apache.org/zh/docsv2.7/user/)
&emsp;&emsp;[Zookeeper教程](https://www.runoob.com/w3cnote/zookeeper-tutorial.html)
## 一、技术介绍与环境准备
### 1、SpringBoot是什么？
>Spring Boot 是 Pivotal 团队在 Spring 的基础上提供的一套全新的开源框架，其目的是为了简化 Spring 应用的搭建和开发过程。Spring Boot 去除了大量的 XML 配置文件，简化了复杂的依赖管理。

>Spring Boot 具有 Spring 一切优秀特性，Spring 能做的事，Spring Boot 都可以做，而且使用更加简单，功能更加丰富，性能更加稳定而健壮。随着近些年来微服务技术的流行，Spring Boot 也成了时下炙手可热的技术。

>Spring Boot 集成了大量常用的第三方库配置，Spring Boot 应用中这些第三方库几乎可以是零配置的开箱即用（out-of-the-box），大部分的 Spring Boot 应用都只需要非常少量的配置代码（基于 Java 的配置），开发者能够更加专注于业务逻辑。  
### 2、什么是dubbo？
>Dubbo(读音[ˈdʌbəʊ])是阿里巴巴公司开源的一个高性能优秀的服务框架，使得应用可通过高性能的 RPC 实现服务的输出和输入功能，可以和Spring框架无缝集成。

>Dubbo是一款高性能、轻量级的开源Java RPC框架，它提供了三大核心能力：
&emsp;(1)、面向接口的远程方法调用
&emsp;(2)、智能容错和负载均衡
&emsp;(3)、服务自动注册和发现<br>
2018 年和当当的Dubbox 进行了合并，进入Apache 孵化器，在 2019 贡献给 Apache 成为顶级项目。

*推荐阅读：[Dubbo介绍的一片好文（来自简书）](https://www.jianshu.com/p/3090d63e9cb3)*

*注：本文无需安装dubbo-admin客户端，网上的文章很乱很杂，别看晕了*
### 3、为什么要用dubbo

> Dubbo的诞生和SOA分布式架构的流行有着莫大的关系。SOA 面向服务的架构(Service Oriented Architecture)，也就是把工程按照业务逻辑拆分成服务层和表现层。服务层中包含业务逻辑，只需要对外提供服务即可。表现层只需要处理和页面的交互，业务逻辑都是调用服务层的服务来实现。
SOA架构中有两个主要角色：服务提供者（Provider）和服务使用者（Consumer）

>随着微服务的进一步发展，服务越来越多，服务之间的调用和依赖关系也越来越复杂，诞生了面向服务的架构体系(SOA)，
也因此衍生出了一系列相应的技术，如对服务提供、服务调用、连接处理、通信协议、序列化方式、服务发现、服务路由、日志输出等行为进行封装的服务框架。
就这样为分布式系统的服务治理框架就出现了，Dubbo也就这样产生了.


下图是Dubbo所解决的需求，以及每一个部分的“分工”

![Dubbo所解决的需求](https://img-blog.csdnimg.cn/img_convert/43247d8c1f6d595ee6f3a01ab8f5ca25.png#pic_center)

> 在大规模服务化之前，应用可能只是通过 RMI 或 Hessian 等工具，简单的暴露和引用远程服务，通过配置服务的URL地址进行调用，通过 F5 等硬件进行负载均衡。<br>
当服务越来越多时，服务 URL 配置管理变得非常困难，F5 硬件负载均衡器的单点压力也越来越大。 此时需要一个服务注册中心，动态地注册和发现服务，使服务的位置透明。并通过在消费方获取服务提供方地址列表，实现软负载均衡和 Failover，降低对 F5 硬件负载均衡器的依赖，也能减少部分成本。<br>
当进一步发展，服务间依赖关系变得错踪复杂，甚至分不清哪个应用要在哪个应用之前启动，架构师都不能完整的描述应用的架构关系。 这时，需要自动画出应用间的依赖关系图，以帮助架构师理清关系。<br>
接着，服务的调用量越来越大，服务的容量问题就暴露出来，这个服务需要多少机器支撑？什么时候该加机器？ 为了解决这些问题，第一步，要将服务现在每天的调用量，响应时间，都统计出来，作为容量规划的参考指标。其次，要可以动态调整权重，在线上，将某台机器的权重一直加大，并在加大的过程中记录响应时间的变化，直到响应时间到达阈值，记录此时的访问量，再以此访问量乘以机器数反推总容量。<br>
以上是 Dubbo 最基本的几个需求。

下图是Dubbo的框架架构

![Dubbo的框架架构](https://img-blog.csdnimg.cn/img_convert/41b30442ed6187d7dcb9e87d4f9144eb.png#pic_center)
**节点角色说明**
|节点|角色说明|
|--|--|
| Provider | 暴露服务的服务提供方 |
| Consumer |  调用远程服务的服务消费方|
|Registry|服务注册与发现的注册中心|
|Monitor|统计服务的调用次数和调用时间的监控中心|
|Container|服务运行容器|

**调用关系说明**

> 0.&ensp;服务容器负责启动，加载，运行服务提供者。
1.&ensp;服务提供者在启动时，向注册中心注册自己提供的服务。
2.&ensp;服务消费者在启动时，向注册中心订阅自己所需的服务。
3.&ensp;注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。
4.&ensp;服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
5.&ensp;服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。

### 4、zookeeper的简单介绍

> ZooKeeper 是 Apache 软件基金会的一个软件项目，它为大型分布式计算提供开源的分布式配置服务、同步服务和命名注册。<br>
ZooKeeper 的架构通过冗余服务实现高可用性。<br>
Zookeeper 的设计目标是将那些复杂且容易出错的分布式一致性服务封装起来，构成一个高效可靠的原语集，并以一系列简单易用的接口提供给用户使用。<br>
一个典型的分布式数据一致性的解决方案，分布式应用程序可以基于它实现诸如数据发布/订阅、负载均衡、命名服务、分布式协调/通知、集群管理、Master 选举、分布式锁和分布式队列等功能。

### 5、zookeeper的安装
因篇幅的原因，对于zookeeper的安装不再赘述，如果未安装zookeeper的话，可参考以下链接进行安装。

[Zookeeper的安装与配置](https://www.runoob.com/w3cnote/zookeeper-setup.html)
## 二、 项目搭建（SpringBoot+Dubbo2.78+zookeeper3.4.14）
下面正式进入本文的正文：**项目的搭建**
### 1、项目结构
![项目结构](https://img-blog.csdnimg.cn/7e60502084844e9f9a7c0a8e56164fed.png#pic_center)
本项目主要由三个模块构成：
dubbo-interface：接口和模型的提供者，负责提供接口和模型
dubbo-provider：服务的提供者，负责在注册中心（zookeeper）进行注册，并对接口进行实现，提供自己的服务
dubbo-consumer：服务的消费者，负责在注册中心（zookeeper）进行订阅，在注册中心订阅自己所需要的服务
### 2、dubbo-interface的实现
#### &emsp;&emsp;项目结构
![dubbo-interface的项目结构](https://img-blog.csdnimg.cn/990f6934a5de4c29b35f0a617a319a1e.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBASyBvIGEgbCBh,size_20,color_FFFFFF,t_70,g_se,x_16)
&emsp;&emsp;&emsp;dubbo-interface项目是一个非常简单的Maven工程，只是提供一个HelloService的接口而已
#### &emsp;&emsp;实现步骤
&emsp;&emsp;&emsp;  1. 创建一个Maven工程
&emsp;&emsp;&emsp;  2. 创建HelloService接口
&emsp;&emsp;&emsp;  3. 对dubbo-interface进行打包并放置仓库，以便当做依赖使用
#### &emsp;&emsp;具体实现过程
&emsp;&emsp;&emsp;   1. 创建一个Maven工程![新建Maven项目](https://img-blog.csdnimg.cn/d2042a3600c94ca0a2e4ae2a0c8096c7.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBASyBvIGEgbCBh,size_20,color_FFFFFF,t_70,g_se,x_16)
&emsp;&emsp;&emsp; 2. 创建HelloService接口

&emsp;&emsp;&emsp; &emsp;创建一个HelloService接口文件，然后写一个sayHello接口方法。

```java
		package com.example.service;
		
		public interface HelloService {
		
		    public String sayHello(String name);
		}
```
&emsp;&emsp;&emsp;  3. 对dubbo-interface进行打包并放置仓库，以便当做依赖使用
&emsp;&emsp;&emsp;&emsp;对Maven工程进行打包是在日常学习/工作编码中经常面对的操作，不会打包，阅读下面链接
&emsp;&emsp;&emsp;&emsp;[Idea打包Maven工程](https://blog.csdn.net/weixin_45666566/article/details/112827301)

> maven常用打包命令
1、mvn compile 编译,将Java 源程序编译成 class 字节码文件。
2、mvn test 测试，并生成测试报告
3、mvn clean 将以前编译得到的旧的 class 字节码文件删除
4、mvn pakage 打包,动态 web工程打 war包，Java工程打 jar 包。
5、mvn install 将项目生成 jar 包放在仓库中，以便别的模块调用
6、mvn clean install -Dmaven.test.skip=true  抛弃测试用例打包

对dubbo-interface工程执行intall打包存放仓库，以便dubbo-provider模块和dubbo-consumer模块进行依赖引入使用。
![打包](https://img-blog.csdnimg.cn/ffa7770634884913bd725646648b8669.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBASyBvIGEgbCBh,size_11,color_FFFFFF,t_70,g_se,x_16)

至此，dubbo-interface就已经完成了。
dubbo-interface模块，主要放业务接口的定义，它是服务消费者模块和服务提供者模块的公共依赖模块。

### 3、dubbo-provider的实现
#### &emsp;&emsp;项目结构
![dubbo-provider工程结构](https://img-blog.csdnimg.cn/4e3f8a8605bb49748e2ffeefe6728490.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBASyBvIGEgbCBh,size_20,color_FFFFFF,t_70,g_se,x_16)
&emsp;&emsp;&emsp;dubbo-provider工程是一个SpringBoot工程，主要是实现dubbo-interface所提供的接口，然后在注册中心（Zookeeper）注册服务
#### &emsp;&emsp;实现步骤
&emsp;&emsp;&emsp;  1. 创建一个SpringBoot工程
&emsp;&emsp;&emsp;  2. 编辑pom.xml文件,添加项目依赖 
&emsp;&emsp;&emsp;  3. 编辑application.properties文件,添加配置信息
&emsp;&emsp;&emsp;  4. 创建HelloServiceImpl类，实现HelloService接口 
&emsp;&emsp;&emsp;  5. 修改启动器，实现dubbo自动配置和包的扫描 
#### &emsp;&emsp;具体实现过程
&emsp;&emsp;&emsp;  1. 创建一个SpringBoot工程
![新建SpringBoot项目](https://img-blog.csdnimg.cn/a473157d61bd4a5185cd21681041b982.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBASyBvIGEgbCBh,size_20,color_FFFFFF,t_70,g_se,x_16)
&emsp;&emsp;&emsp;  2. 编辑pom.xml文件,添加项目依赖 
&emsp;&emsp;&emsp;&emsp;&ensp;创建完dubbo-provider工程后，我们显示需要做的是编辑pom.xml文件导入项目需要的依赖
&emsp;&emsp;&emsp;&emsp;&ensp;我们需要在pom.xml文件中加入三个依赖：接口依赖、dubbo的依赖、zookeeper的依赖

```java
		<!--引入dubbo-interface接口依赖-->
		<dependency>
            <groupId>org.example</groupId>
            <artifactId>dubbo-interface</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <!--引入dubbo的依赖-->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>2.7.8</version>
        </dependency>

        <!-- 引入zookeeper的依赖 -->
        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-recipes</artifactId>
            <version>2.12.0</version>
        </dependency>

        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-framework</artifactId>
            <version>2.12.0</version>
        </dependency>
```
在引入依赖时需要注意的几个点是：dubbo版本在2.7.8之前的依赖(dubbo-spring-boot-starter)来自于com.alibaba.spring.boot,而dubbo版本在2.7.8之后(包含dubbo2.7.8)的依赖来自于org.apache.dubbo,引入时需要注意自己的dubbo版本；然后引入zookeeper的依赖时，可以导入zkclient，也可以导入curator-recipes和curator-framework,可以根据自己的需求添加，值得注意的是版本号要注意自己zookeeper版本调整，不然容易报错，无法链接zookeeper注册中心。

pom.xml文件完整内容

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.2</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.example</groupId>
    <artifactId>dubbo-provider</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>dubbo-provider</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
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
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.example</groupId>
            <artifactId>dubbo-interface</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <!--引入dubbo的依赖-->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>2.7.8</version>
        </dependency>

        <!-- 引入zookeeper的依赖 -->
        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-recipes</artifactId>
            <version>2.12.0</version>
        </dependency>

        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-framework</artifactId>
            <version>2.12.0</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```
&emsp;&emsp;&emsp;  3. 编辑application.properties文件,添加配置信息
&emsp;&emsp;&emsp;&emsp; 完成pom文件的配置之后，接着配置dubbo和zookeeper的一些基础配置
&emsp;&emsp;&emsp;&emsp; 我们需要在application.properties文件配置服务端口、dubbo服务的id和name属性以及zookeeper的注册地址

```java
# 配置端口
server.port=8088

#服务id
dubbo.application.id=dubbo-provider
#服务name
dubbo.application.name=dubbo-provider
#注册中心的ip地址，本项目使用的是本地注册中心，如使用的虚拟机或者服务器注册中心，请自行修改
dubbo.registry.address=zookeeper://127.0.0.1:2181
```
&emsp;&emsp;&emsp;&emsp; 本教程关于配置文件的编辑使用的是application.properties文件，如使用application.yaml文件进行配置，请自行进行编辑
&emsp;&emsp;&emsp;  4. 创建HelloServiceImpl类，实现HelloService接口
 &emsp;&emsp;&emsp;&emsp;完成项目的基础配置后：实现服务，创建HelloServiceImpl类，实现HelloService接口

```java
package com.example.dubboprovider.service.impl;

import com.example.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@Component //标注一个类为Spring容器的Bean
@DubboService//service服务层（注入dao）
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}
```
HelloServiceImpl文件没有什么需要解释的，就是DubboService注解需要注意一下，Dubbo版本在2.7.8及其以后版本，要将Service注解改为DubboService注解，不然会无法正常创建bean对象。
&emsp;&emsp;&emsp;  5. 修改启动器，实现dubbo自动配置和包的扫描 

```java
package com.example.dubboprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication//SpringBoot启动器注解
@EnableDubbo(scanBasePackages = "com.example.dubboprovider")//dubbo自动注册
@PropertySource("classpath:/application.properties")//扫描配置文件地址
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}
```
#### &emsp;&emsp;启动项目
&emsp;&emsp;&emsp;&emsp;其实SpringBoot工程启动是非常容易的，只需要运行启动器。
&emsp;&emsp;&emsp;&emsp;*注：记得启动zookeeper！记得启动zookeeper！记得启动zookeeper！*
&emsp;&emsp;&emsp;&emsp;运行成功会出现下面的日志
![日志](https://img-blog.csdnimg.cn/eb17ce3d5c2a4119823ae957d98d2719.png)
#### &emsp;&emsp;注册中心验证
&emsp;&emsp;&emsp;&emsp;服务提供者成功启动后可以到zookeeper注册中心进行验证，验证方式如图所示
![在这里插入图片描述](https://img-blog.csdnimg.cn/30abef5cb37d47d887f2ef37ba971dbd.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBASyBvIGEgbCBh,size_20,color_FFFFFF,t_70,g_se,x_16)
同时推荐一个zookeeper可视化软件---prettyZoo
github的地址：[prettyZoo](https://github.com/vran-dev/PrettyZoo/releases)
如果懒得上github下载，也可以用下面的链接进行下载
*~~欠两天~~ *

### 4、dubbo-consumer的实现
整个dubbo-consumer的实现和dubbo-provider的实现有着很多的相似之处，后面的实现就不再有过多的语言赘述
#### &emsp;&emsp;项目结构
![在这里插入图片描述](https://img-blog.csdnimg.cn/dda4846cd74f40d5903b2b4c8baecb7b.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBASyBvIGEgbCBh,size_19,color_FFFFFF,t_70,g_se,x_16)
#### &emsp;&emsp;实现步骤
&emsp;&emsp;&emsp;  1. 创建一个SpringBoot工程
&emsp;&emsp;&emsp;  2. 编辑pom.xml文件,添加项目依赖 
&emsp;&emsp;&emsp;  3. 编辑application.properties文件,添加配置信息
&emsp;&emsp;&emsp;  4. 创建HelloController类，订阅hello服务 
&emsp;&emsp;&emsp;  5. 修改启动器，实现dubbo自动配置和包的扫描 
#### &emsp;&emsp;具体实现过程
&emsp;&emsp;&emsp;  1. 创建一个SpringBoot工程
![在这里插入图片描述](https://img-blog.csdnimg.cn/483423f2a20745409b6f7848cea15591.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBASyBvIGEgbCBh,size_20,color_FFFFFF,t_70,g_se,x_16)

&emsp;&emsp;&emsp;  2. 编辑pom.xml文件,添加项目依赖 

```java
<dependency>
            <groupId>org.example</groupId>
            <artifactId>dubbo-interface</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <!--引入dubbo的依赖-->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>2.7.8</version>
        </dependency>

        <!-- 引入zookeeper的依赖 -->
        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-recipes</artifactId>
            <version>2.12.0</version>
        </dependency>

        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-framework</artifactId>
            <version>2.12.0</version>
        </dependency>
```
完整的pom.xml

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.2</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.example</groupId>
    <artifactId>dubbo-consumer</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>war</packaging>
    <name>dubbo-consumer</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
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
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.example</groupId>
            <artifactId>dubbo-interface</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <!--引入dubbo的依赖-->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>2.7.8</version>
        </dependency>

        <!-- 引入zookeeper的依赖 -->
        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-recipes</artifactId>
            <version>2.12.0</version>
        </dependency>

        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-framework</artifactId>
            <version>2.12.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

&emsp;&emsp;&emsp;  3. 编辑application.properties文件,添加配置信息

```java
# 配置端口
server.port=8085

dubbo.application.id=dubbo-consumer
dubbo.application.name=dubbo-consumer
dubbo.registry.address=zookeeper://127.0.0.1:2181
```
*注意：服务的提供者和消费者服务端口不可以相同*

&emsp;&emsp;&emsp;  4. 创建HelloController类，订阅hello服务 

```java
package com.example.dubboconsumer.controller;

import com.example.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @DubboReference
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello() {
        String hello = helloService.sayHello("world");
        System.out.println(helloService.sayHello("liang"));
        return hello;
    }
}
```

&emsp;&emsp;&emsp;  5. 修改启动器，实现dubbo自动配置和包的扫描

```java
package com.example.dubboconsumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.example.dubboconsumer")
@PropertySource("classpath:/application.properties")
public class DubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }

}
```

#### &emsp;&emsp;启动消费者并测试
在最后，启动消费者的启动器，然后在浏览器输入http://localhost:8085/hello，即是调用注册中心服务的结果
![在这里插入图片描述](https://img-blog.csdnimg.cn/969bd95aea66465eb76a44a30c9346a3.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBASyBvIGEgbCBh,size_19,color_FFFFFF,t_70,g_se,x_16)
控制台也会有输出
![在这里插入图片描述](https://img-blog.csdnimg.cn/19de146e84a74d4db58855a0ed9d9d95.png)
注册中心的注册信息如下
![在这里插入图片描述](https://img-blog.csdnimg.cn/668a02ebf4074de398ba06f4764593e2.png)

到此，本次项目的搭载到此圆满结束。谢谢Thanks♪(･ω･)ﾉ

文章有很多不足，可以评论指正，我进行修改。

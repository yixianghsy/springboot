# [Docker安装ElasticSearch5.6.8](https://www.cnblogs.com/yuanfeii/p/15352389.html)

#### 前言[#](https://www.cnblogs.com/yuanfeii/p/15352389.html#前言)

环境准备

```shell
系统：ubuntu18.0.4  软件：docker  ElasticSearch版本：5.6.8
```

步骤1: 拉取镜像

```shell
docker pull elasticsearch:5.6.8
```

如下载速度比较慢，可以更改docker镜像源

```shell
vi /etc/docker/daemon.json

# 添加以下内容
{
"registry-mirrors": ["http://hub-mirror.c.163.com"]
}
```

步骤2：修改docker宿主机参数配置

```shell
vi /etc/security/limits.conf

# 追加以下内容 nofifile 是单个进程允许打开的最大文件个数 soft nofifile 是软限制 hard nofifile是硬限制修改
* soft nofile 65536
* hard nofile 65536

vi /etc/sysctl.conf
# 追加以下内容
vm.max_map_count=655360
```

步骤3：启动并修改ElasticSearch.yml配置

```shell
# 启动容器
docker run -d --name=elasticsearch -p 9200:9200 -p 9300:9300 --restart=always elasticsearch:5.6.8

# 进入容器
docker exec -it 容器ID /bin/bash

# 没有vim编辑器
apt-get update
apt-get install -y vim

# 打开配置文件
vim /config/elasticsearch.yml

# 使用transportClient一定要打开
transport.host=0.0.0.0

# 修改跨域同源设置
http.cors.enabled: true
http.cors.allow-origin: "*"
```
---
author: "lei"
date: "2022-10-30"
title: hexo_nginx简单配置
tags: "Other"
categories: "Other"
cover: /imagers/image-20221030012437991.png
top_img: /imagers/image-20221030012437991.png
---



##### 1.安装Git

```
# 安装Git
sudo apt-get install git-core
```

##### 2.安装Node.js

进入Node.js中文官网下载Linux二进制文件：http://nodejs.cn/download/
```
# 下载Linux二进制文件
wget https://npmmirror.com/mirrors/node/v16.14.2/node-v16.14.2-linux-x64.tar.xz
# 解压文件
tar -xvf node-v16.14.2-linux-x64.tar.xz
# 移动到/usr/local/node下
sudo mv node-v16.14.2-linux-x64 /usr/local/node/node-v16.14.2-linux-x64
# 将node和npm设为全局变量
sudo ln -s /usr/local/node/node-v16.14.2-linux-x64/bin/node /usr/local/bin/node
sudo ln -s /usr/local/node/node-v16.14.2-linux-x64/bin/npm /usr/local/bin/npm
```

##### 3.安装Hexo

```
# 安装Hexo
npm install -g hexo-cli
# 将hexo命令添加为全局变量
sudo ln -s /usr/local/node/node-v16.13.0-linux-x64/bin/hexo /usr/local/bin/hexo
```

##### 4.初始化项目文件

```
# 跳转到创建目录
cd /home/ubuntu
# 初始化文件
hexo init blog
cd blog
npm install
```

##### 5.安装nginx

```
#安装
sudo apt install nginx
#查看路径
whereis nginx
#找到nginx.conf
cd /etc/nginx/
#编辑
vim nginx.conf
```

##### 6.配置nginx

```
    server {
        listen       80;
        server_name  *.lei90.com; # 浏览器访问域名
        charset utf-8;
        # 路由
        location / {
            root   /home/lighthouse/myblog/public; # 访问根目录
            index  index.html index.htm; # 入口文件
        }
    }

```

##### 7.重启nginx

```
sudo nginx -s stop
sudo nginx
```

##### Nginx 403 forbidden 错误的原因及解决方法

```
一、由于启动用户和nginx工作用户不一致所致 将nginx.config的user改为和启动用户一致，
user root;
#查看启动用户
ps aux | grep nginx

lighthouse@VM-12-2-ubuntu:~/myblog$ ps aux | grep nginx
root     30620  0.0  0.0 142192  1572 ?        Ss   01:16   0:00 nginx: master process nginx
root     30621  0.0  0.1 145152  7164 ?        S    01:16   0:00 nginx: worker process
root     30622  0.0  0.1 144856  6236 ?        S    01:16   0:00 nginx: worker process
lightho+ 31431  0.0  0.0  14824  1072 pts/2    S+   01:20   0:00 grep --color=auto nginx
```


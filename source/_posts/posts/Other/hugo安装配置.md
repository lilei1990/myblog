---
author: "lei"
date: "2019-03-05"
title: hugo安装配置
tags: "Other"
categories: "Other"
cover: /imagers/image-20221029045216291.png
top_img: /imagers/image-20221029045216291.png
---

## ubuntu

```
sudo apt-get install hugo  //安装hugo
```

## windows

```
去官网 https://github.com/gohugoio/hugo/releases/tag/v0.101.0  下载相应的安装包

配置环境变量,bin目录放置exe执行文件
D:\hugo\bin
```

### 安装

```
//查看版本
hugo version  

//创建新站点
hugo new site quickstart 

//添加主题
git submodule add https://github.com/theNewDynamic/gohugo-theme-ananke.git themes/ananke 
```

## 配置站点

```
baseURL = 'http://lei90.com/'
languageCode = 'en-us'
title = '被迫内卷'
theme = "papermod"
disablePathToLower = true
relativeURLS = true
```

## 运行站点

```
//把md文档复制到post目录下,运行服务  http://localhost:1313/
hugo server -D 
//生成静态网站,到public目录下
hugo -D
```


---
author: "lei"
date: "2019-03-05"
title: gradle配置比较
tags: "Other"
categories: "Other"
cover: /imagers/image-20221029045347673.png
top_img: /imagers/image-20221029045347673.png
---

#### 解决依赖冲突
##### **方式一**

在app的build.gradle中添加类似配置如下：

    configurations.all {
        resolutionStrategy {
            //解决v4包冲突，强制使用这个版本的v4包
            force 'com.android.support:support-v4:26.1.0'
        }
    }

##### **方式二**

    exclude
    
    implementation("com.xxx.xxx:xx") {
         exclude group: 'com.android.support'
    }

exclude是最常用的解决依赖冲突的方式，但如果多个依赖库引入不同版本的其它库，需要分别写好多个exclude，显然第一种方式比较简单粗暴。
README-CN.md

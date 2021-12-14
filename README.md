# SVRedirector
这是一个 Java Agent 程序，用于让 BuildTools 从我的 spigotversions 仓库获取版本数据。
此程序工作原理十分简单，就是给 org.spigotmc.builder.Builder 的 get 方法的开头增加了一行替换 (replaceFirst) 语句。

## 自己构建?
下载这个仓库的代码，自行安装 Maven 构建 (mvn clean package 即可)。

## 版权
版权所有 (C) 2021 SNWCreations。本软件是自由软件，使用 GPLv3 授权。

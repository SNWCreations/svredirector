# SVRedirector

这是一个 Java Agent 程序，用于让 BuildTools 从我的 spigotversions 仓库获取版本数据。
此程序工作原理十分简单，就是给 org.spigotmc.builder.Builder 的 get 方法的开头增加了一行替换 (replaceFirst) 语句。

## 用法

`java -javaagent:<svredirector 文件的绝对路径>[=<镜像源名称>] -jar BuildTools.jar <...>`

例如 `java -javaagent:svredirector.jar=ghproxy -jar BuildTools.jar --rev 1.12.2`

如果你不知道有哪些镜像源可供选择，可以像执行普通 Java 程序一样执行此程序，程序会输出可用的镜像源名称。

命令是 `java -jar <svredirector 文件的绝对路径>`

## 自己构建?
下载这个仓库的代码，自行安装 Maven 构建 (mvn clean package 即可)。

## 版权
版权所有 (C) 2021, 2022 SNWCreations。本软件是自由软件，使用 GPLv3 授权。

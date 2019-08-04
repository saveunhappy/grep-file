# IO与异常实战：在文件中查找匹配的行号

请完成[`FileSearch`](https://github.com/hcsp/grep-file/blob/master/src/main/java/com/github/hcsp/io/FileSearch.java)中的程序，找到第一个包含指定文本的行的行号，行号从1开始计算。若没找到，则返回-1。

例如，给定一个文件：

```
AAA
BBB
CCC
```

则查找BBB的结果是2，查找DDD的结果是-1。注意，文件可能很大，超过了内存的大小。

-----
注意！我们只允许你修改以下文件，对其他文件的修改会被拒绝：
- [src/main/java/com/github/hcsp/io/FileSearch.java](https://github.com/hcsp/grep-file/blob/master/src/main/java/com/github/hcsp/io/FileSearch.java)
- [pom.xml](https://github.com/hcsp/grep-file/blob/master/pom.xml)
-----


完成题目有困难？不妨来看看[写代码啦的相应课程](https://xiedaimala.com/tasks/661cd7ab-7fea-47d0-8e11-555d6fca751d)吧！

回到[写代码啦的题目](https://xiedaimala.com/tasks/661cd7ab-7fea-47d0-8e11-555d6fca751d/quizzes/6c87ef57-7f06-4af2-9112-86dd27ff099d)，继续挑战！

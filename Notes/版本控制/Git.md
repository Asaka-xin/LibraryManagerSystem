### 1. 三种状态

现在请注意，如果你希望后面的学习更顺利，请记住下面这些关于 Git 的概念。 Git 有三种状态，你的文件可能处于其中之一： **已提交（committed）**、**已修改（modified）** 和 **已暂存（staged）**。

- 已修改表示修改了文件，但还没保存到数据库中。
- 已暂存表示对一个已修改文件的当前版本做了标记，使之包含在下次提交的快照中。
- 已提交表示数据已经安全地保存在本地数据库中。

这会让我们的 Git 项目拥有三个阶段：工作区、暂存区以及 Git 目录。

![工作区、暂存区以及 Git 目录。](C:\Workspace\git-study\areas.86c16db0.png)

工作目录、暂存区域以及 Git 仓库.

工作区是对项目的某个版本独立提取出来的内容。 这些从 Git 仓库的压缩数据库中提取出来的文件，放在磁盘上供你使用或修改。

暂存区是一个文件，保存了下次将要提交的文件列表信息，一般在 Git 仓库目录中。 按照 Git 的术语叫做“索引”，不过一般说法还是叫“暂存区”。

Git 仓库目录是 Git 用来保存项目的元数据和对象数据库的地方。 这是 Git 中最重要的部分，从其它计算机克隆仓库时，复制的就是这里的数据。

基本的 Git 工作流程如下：

1. 在工作区中修改文件。
2. 将你想要下次提交的更改选择性地暂存，这样只会将更改的部分添加到暂存区。
3. 提交更新，找到暂存区的文件，将快照永久性存储到 Git 目录。

如果 Git 目录中保存着特定版本的文件，就属于 **已提交** 状态。 如果文件已修改并放入暂存区，就属于 **已暂存** 状态。 如果自上次检出后，作了修改但还没有放到暂存区域，就是 **已修改** 状态。

如上，如果每个版本中有文件发生变动，Git 会将整个文件复制并保存起来。这种设计看似会多消耗更多的空间，但在分支管理时却是带来了很多的益处和便利。

### 2. Git 保证完整性

Git 中所有的数据在存储前都计算校验和，然后以校验和来引用。 这意味着不可能在 Git 不知情时更改任何文件内容或目录内容。 这个功能建构在 Git 底层，是构成 Git 哲学不可或缺的部分。 若你在传送过程中丢失信息或损坏文件，Git 就能发现。

Git 用以计算校验和的机制叫做 SHA-1 散列（hash，哈希）。 这是一个由 40 个十六进制字符（0-9 和 a-f）组成的字符串，基于 Git 中文件的内容或目录结构计算出来。

Git 中使用这种哈希值的情况很多，你将经常看到这种哈希值。 实际上，Git 数据库中保存的信息都是以文件内容的哈希值来索引，而不是文件名。

### 3.git换行符差异

git上传代码报错-

The file will have its original line endings in your working directory

**原因是因为文件中换行符的差别导致的**

windows下的换行符是CRLF而Unix的换行符格式是LF。git默认支持LF。

上面的报错的意思是会把CRLF（也就是回车换行）转换成Unix格式（LF），这些是转换文件格式的警告，不影响使用。

一般commit代码时git会把CRLF转LF，pull代码时LF换CRLF。

解决方案

```bash
# 移除现有缓存
git rm -r --cached .
# 更改config设置
git config core.autocrlf false
```

为true时，Git会将你add的所有文件视为文本问价你，将结尾的CRLF转换为LF，而checkout时会再将文件的LF格式转为CRLF格式

为false时，line endings不做任何改变，文本文件保持其原来的样子

为input时，add时Git会把CRLF转换为LF，而check时仍旧为LF，所以Windows操作系统不建议设置此值

# 使用

#### 1.初始化

##### 1.初次运行 Git 前的配置

```bash
$ git config --list --show-origin
```

##### 2.签名

```bash
$ git config --global user.name "name_"
$ git config --global user.email "e-mail_"
```

#### 2.检查配置

查看当前的配置信息

```bash
$ git config --list
```

查看某一项配置

```bash
$ git config <key>
# (user.name/email)
```

#### 3.命令

##### 1.查看暂存区

```bash
$ git status
# 出现红色文件是未被跟踪的文件，即现有仓库匹配不到的文件
```

##### 2.查看提交日志

```bash
# 获取简洁的提交日志
$ git log --graph
```

#### 4.版本回退

##### 1.回滚快照

*注：快照即提交的版本，每个版本我们称之为一个快照。*

```bash
# HEAD代表当前分支的最新提交
$ git reset HEAD
```

*注：HEAD 表示最新提交的快照，而 HEAD~ 表示 HEAD 的上一个快照，HEAD~~表示上上个快照，如果表示上10个快照，则可以用HEAD ~10*

--hard : 回退版本库，暂存区，工作区。（因此我们修改过的代码就没了，需要谨慎使用）

reset 不仅移动 HEAD 的指向，将快照回滚动到暂存区域，它还将暂存区域的文件还原到工作目录。

--mixed: 回退版本库，暂存区。(--mixed为git reset的默认参数，即当任何参数都不加的时候的参数)

--soft: 回退版本库。

就相当于只移动 HEAD 的指向，但并不会将快照回滚到暂存区域。相当于撤消了上一次的提交（commit）。

#### 5.版本对比

##### 1、暂存区与工作树

目的：对比版本之间有哪些不同

在已经存在的文件b.txt中添加内容：

```bash
$ git diff
diff --git a/b.txt b/b.txt
index 9ab39d5..4d37a8a 100644
--- a/b.txt
+++ b/b.txt
@@ -2,3 +2,4 @@
 1212
 123123123
 234234234
+手动阀手动阀
```

现在来解释一下上面每一行的含义：

**第一行：**diff --git a/b.txt b/b.txt 表示对比的是存放在暂存区域和工作目录的b.txt

**第二行：**index 9ab39d5..4d37a8a 100644 表示对应文件的 ID 分别是 9ab39d5和 4d37a8a，左边暂存区域，后边当前目录。最后的 100644 是指定文件的类型和权限

**第三行：**--- a/b.txt

--- 表示该文件是旧文件（存放在暂存区域）

**第四行：**+++ b/b.txt +++ 表示该文件是新文件（存放在工作区域）

**第五行：**@@ -2,3 +2,4 @@ 以 @@ 开头和结束，中间的“-”表示旧文件，“+”表示新文件，后边的数字表示“开始行号，显示行数”

内容：+代表新增的行 -代表少了的行

直接执行 git diff 命令是比较暂存区域与工作目录的文件内容:

##### 2.从暂存区返回文件

```bash
# <file.name_>为文件名
$ git checkout -- <file.name_>
```

##### 3.取消文件跟踪

```bash
# <file.name_> 为文件名
$ git rm <file.name_>
```

#### 6.重命名文件

**不能直接在工作目录重命名文件**

正确的方式

```bash
# file01为旧文件，file02为新文件名
$ git mv file01 fiel02
```

#### 7.添加忽略规则

1. 在工作目录创建一个名为 .gitignore 的文件
2. 利用CMD创建$ echo *.temp > .gitignore//把.temp后缀的文件忽略

#### 8.创建分支

##### 1.新建分支

```bash
# feature01为新的分支名称
$ git branch feature01
```

##### 2.切换分支

指将当前工作环境切换到其他分支上

```bash
# feature01为分支名称
$ git checkout feature01
```

#### 9.合并分支

```bash
# 先切换到主分支master
# 再使用如下命令合并分支
# feature1为分支名称
$ git merge feature1
```

#### 10.删除分支

使用 git branch -d 分支名 命令：

```bash
# feature1为被删除分支名称
$ git branch -d feature1
```

#### 11.变基

合并commit，将本地多次commit合并成一个commit，同时可以修改commit的描述

```bash
# 合并前两次的commit
git  rebase -i head~~s

# 合并此次commit在最新commit的提交
git rebaser -i hash值
```

## 拓展：远程仓库

### 1.添加远程仓库

```bash
# origin为自定义仓库名，<url>直接填写仓库地址
$ git remote add origin <url>
```

### 2.推送到远程仓库

```bash
# 推送到origin仓库的master分支上
$ git push -u origin master
```

### 3.显示某个远程仓库信息

```bash
# [remote]为仓库名称
$ git remote show [remote]
```

### 4.其他

```bash
 $ git push -u origin master 
```

如果当前分支与多个主机存在追踪关系，则可以使用 -u 参数指定一个默认主机，这样后面就可以不加任何参数使用git push

# 其他帮助文档

[协同开发](https://www.ydlclass.com/doc21xnv/basics/git/#%E4%B8%89%E3%80%81%E4%B8%80%E8%88%AC%E7%9A%84%E5%8D%8F%E5%90%8C%E5%BC%80%E5%8F%91%E6%B5%81%E7%A8%8B)


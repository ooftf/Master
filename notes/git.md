# 操作
    Git global setup
    git config --global user.name "lihang36"
    git config --global user.email "lihang9@jd.com"
## SSH
    https://git-scm.com/book/zh/v2/%E6%9C%8D%E5%8A%A1%E5%99%A8%E4%B8%8A%E7%9A%84-Git-%E7%94%9F%E6%88%90-SSH-%E5%85%AC%E9%92%A5
    https://help.github.com/en/articles/connecting-to-github-with-ssh
## 一般git操作
#### 从服务端拉取代码（Create a new repository）
    git clone http://git.jd.com/lihang36/ShareModule.git
#### 从服务器拉取所有分支代码
    git clone xxx
    cd xxx
    git branch -r | grep -v '\->' | while read remote; do git branch --track "${remote#origin/}" "$remote"; done
    git fetch --all
    git pull --all
    push到新的git地址
    git push --mirror https://github.com/ooftf/Jdm.git
#### 从服务端拉取指定文件夹
    git init TIM_Android
    cd TIM_Android
    git remote add origin https://github.com/tencentyun/TIMSDK.git
    git config core.sparsecheckout true
    echo "Android/*" >> .git/info/sparse-checkout
    git pull origin master    
### 提交代码
    git add README.md
    git commit -m "add README"
    git push -u origin master

### 已存在文件和git建立连接（Existing folder）


    第一步

    cd existing_folder
    git init
    git remote add origin git@git.jd.com:lihang36/demo.git

    第二步

    git add .
    git commit -m "Initial commit"
    git push -u origin master
### 已存在的git库（Existing Git repository）
    cd existing_repo
    git remote add origin git@git.jd.com:lihang36/demo.git
    git push -u origin --all
    git push -u origin --tags
### 通过命令行修改远程地址
    git remote set-url origin https://gitee.com/jouypub/json.git
### 从一个git仓库迁移到另外一个git仓库
    https://blog.csdn.net/nathan1987_/article/details/78529357
## submodule 相关
### 添加 submodule
    git submodule add http://git.jd.com/JmClient/JmShareModule.git
### 将所有 submodule切换到master分支
    git submodule foreach "git cheackout master"
### 从remote拉取某个submodule
    1. 第一种方式
        cd submodule_name
        git submodule init 初始化子模块
        git submodule update 更新子模块
    2. 第二种方式
        在父module中
        git submodule update --init --recursive

### 删除submodule（暂定）
    git rm submodule_name
    git commit -m "remove submodule"
    git push origin master
# 问题
## Fetch和pull的区别 ？
    Fetch从远端检出一个新的本地分支
    pull将远程分支和本地分支合并
# error
### already exists in the index
    git rm -r --cached directory
    git commit -m "removing directory"



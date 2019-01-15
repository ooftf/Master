# 操作
    Git global setup
    git config --global user.name "lihang36"
    git config --global user.email "lihang9@jd.com"
## SSH
https://git-scm.com/book/zh/v2/%E6%9C%8D%E5%8A%A1%E5%99%A8%E4%B8%8A%E7%9A%84-Git-%E7%94%9F%E6%88%90-SSH-%E5%85%AC%E9%92%A5
## 一般git操作
#### 从服务端拉取代码（Create a new repository）
    git clone http://git.jd.com/lihang36/ShareModule.git

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

## submodule 相关
### 添加 submodule
    git submodule add http://git.jd.com/JmClient/JmShareModule.git

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


# error
### already exists in the index
    git rm -r --cached directory
    git commit -m "removing directory"



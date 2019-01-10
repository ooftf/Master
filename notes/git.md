
检出项目到文件夹ETD-RN : git clone https://github.com/ooftf/ETD-RN.git

git submodule add http://git.jd.com/JmClient/JmShareModule.git

git clone http://git.jd.com/lihang36/ShareModule.git



Git global setup
git config --global user.name "lihang36"
git config --global user.email "lihang9@jd.com"

Create a new repository
git clone git@git.jd.com:lihang36/demo.git
cd demo
touch README.md
git add README.md
git commit -m "add README"
git push -u origin master

Existing folder
cd existing_folder
git init
git remote add origin git@git.jd.com:lihang36/demo.git
git add .
git commit -m "Initial commit"
git push -u origin master

Existing Git repository
cd existing_repo
git remote add origin git@git.jd.com:lihang36/demo.git
git push -u origin --all
git push -u origin --tags

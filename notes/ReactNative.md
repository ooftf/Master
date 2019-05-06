# 命令
## 设置镜像
npm config set registry https://registry.npm.taobao.org --global
npm config set disturl https://npm.taobao.org/dist --global
## 安装yarn应用
npm install -g yarn react-native-cli
## 设置镜像
yarn config set registry https://registry.npm.taobao.org --global
yarn config set disturl https://npm.taobao.org/dist --global
## 创建一个新的RN项目
react-native init AwesomeProject
react-native init AwesomeProject --version 0.44.3
## 运行android app
react-native run-android
## 打包jsBundle
react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.android.bundle --assets-dest android/app/src/main/res
react-native bundle --platform android --dev false --entry-file index.js --bundle-output build/assets/index.android.bundle --assets-dest build/res/
# [yarn命令](https://yarn.bootcss.com/docs/usage/)

# react-native run-android
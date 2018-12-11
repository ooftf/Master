
### 获取密钥详情
keytool -list -v -alias master -keystore C:\StudioProjects\Master\app\lihang.jks

### 项目打印依赖
gradlew :app:dependencies

### 更新submodule
git submodule update --init --recursive

## 强制使用指定版本
configurations.all {

    resolutionStrategy {
        force 'com.android.support:appcompat-v7:26.1.0'
        force "com.android.support:support-v4:${supportVersion}"
        force "com.android.support:appcompat-v7:${supportVersion}"
        force "com.android.support:design:${supportVersion}"
        force "com.android.support:recyclerview-v7:${supportVersion}"
        force "com.android.support:cardview-v7:${supportVersion}"
        force "com.android.support:gridlayout-v7:${supportVersion}"
        force "com.android.support:support-annotations:${supportVersion}"
    }
}
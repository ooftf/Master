import org.gradle.api.JavaVersion



object Deps{
    const val packerNG = "com.mcxiaoke.packer-ng:plugin:2.0.1"
    const val packerApi = "com.mcxiaoke.packer-ng:helper:2.0.1"
    const val bindingcollectionadapter =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter:3.1.1"
    const val bindingcollectionadapter_recycler =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:3.1.1"
    const val smartRefreshLayout = "com.scwang.smartrefresh:SmartRefreshLayout:1.1.0-andx-8"
    const val smartRefreshHeader = "com.scwang.smartrefresh:SmartRefreshHeader:1.1.0-andx-8"
    const val x5Webview = "com.tencent.tbs.tbssdk:sdk:43993"
    const val material = "com.google.android.material:material:1.3.0"
    const val banner = "io.github.youth5201314:banner:2.2.2"
    const val MMKV = "com.tencent:mmkv-static:1.2.7"
    const val matisse = "com.zhihu.android:matisse:0.5.2-beta4"
    const val disklrucache = "com.jakewharton:disklrucache:2.0.2"
    const val fastJson = "com.alibaba:fastjson:1.2.69"
    const val bottomBar = "com.roughike:bottom-bar:2.3.1"
    const val roundedImageView = "com.makeramen:roundedimageview:2.3.0"
    const val pageIndicatorView  = "com.romandanylyk:pageindicatorview:1.0.2"
    const val logger = "com.orhanobut:logger:2.2.0"
    const val photoView = "com.github.chrisbanes:PhotoView:2.3.0"
    const val glideCompiler = "com.github.bumptech.glide:compiler:4.12.0"
    const val glideApi = "com.github.bumptech.glide:glide:4.12.0"
    const val utilCodeX = "com.blankj:utilcodex:1.30.6"

    const val tourGuide = "com.github.worker8:tourguide:1.0.19-SNAPSHOT@aar"


    const val asyncHttp = "com.loopj.android:android-async-http:1.4.9"
    const val fileDownloader = "com.liulishuo.filedownloader:library:1.7.1"
    const val pinEntryEdittext = "com.alimuzaffar.lib:pinentryedittext:1.3.1"
    const val swipeLayout = "com.daimajia.swipelayout:library:1.2.0@aar"
    const val gson = "com.google.code.gson:gson:2.8.6"

    const val blockCanary =  "com.github.markzhai:blockcanary-android:1.5.0"
    const val jackson =  "com.fasterxml.jackson.core:jackson-databind:2.10.4"
    const val textDrawable =  "com.amulyakhare:com.amulyakhare.textdrawable:1.0.1"
    const val bugly = "com.tencent.bugly:crashreport_upgrade:latest.release"
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.7"

}

/*object versions {
    val compileSdk = 30
    val minSdk = 21
    val targetSdk = 26
    val butterknife = "10.1.0"
    val tinker = "1.9.9"
    val tinkerpatch = "1.2.9"
    val packerNG = "2.0.1"
    val glide = "4.11.0"
    val java = JavaVersion.VERSION_1_8
    val rxlifecycle = "4.0.1"
    val master = "1.0.1-SNAPSHOT"
    val kotlin = "1.5.21"
}

object deps {
    val fastjson = "com.alibaba:fastjson:1.1.71.android"
    val HiHttp = "com.ooftf:hi-http:4.2.4"
    val MMKV = "com.tencent:mmkv-static:1.2.7"
    val progressDrawable = "com.github.ooftf:progress-drawable:1.2.0"
    val disklrucache = "com.jakewharton:disklrucache:2.0.2"

    val matisse = "com.zhihu.android:matisse:0.5.2-beta4"
    val CalendarView = "com.github.ooftf:CalendarView:1.0.5"
    val ARouterCompiler = "com.alibaba:arouter-compiler:1.5.1"
    val ARouterApi = "com.alibaba:arouter-api:1.5.1"
    val bindingcollectionadapter =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter:3.1.1"
    val bindingcollectionadapter_recycler =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:3.1.1"
    val SmartRefreshLayout = "com.scwang.smartrefresh:SmartRefreshLayout:1.1.0-andx-8"
    val SmartRefreshHeader = "com.scwang.smartrefresh:SmartRefreshHeader:1.1.0-andx-8"
    val material = "com.google.android.material:material:1.3.0"
    val banner = "io.github.youth5201314:banner:2.2.2"
    val kvLayout = "com.github.ooftf:kv-layout:1.0.9"
    val imagePreview = "com.github.ooftf:image-preview:2.3.1"
    val basic = "com.github.ooftf:basic:0.4.2"
    val afm = "com.github.ooftf:arch-frame-mvvm:0.2.2"
    val x5Webview = "com.tencent.tbs.tbssdk:sdk:43993"
    val statelayout = "com.github.ooftf:master-widget-statelayout:1.1.3"
    val toolbar = "com.github.ooftf:master-widget-toolbar:1.1.8"
    val dataBindingEx = "com.github.ooftf:dataBinding-extensions:1.2.6"
    val tablayoutPro = "com.github.ooftf:tablay"
    val dialog = "com.github.ooftf:master-widget-dialog:1.2.4"
    val HpptUiMapping = "com.github.ooftf:http-ui-mapping:1.4.2"
}

object androidx {
    val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    val appcompat = "androidx.appcompat:appcompat:1.2.0"
    val vectordrawable = "androidx.vectordrawable:vectordrawable:1.1.0-beta02"
    val cardview = "androidx.cardview:cardview:1.0.0"
    val recyclerview = "androidx.recyclerview:recyclerview:1.2.0"
    val legacy_v4 = "androidx.legacy:legacy-support-v4:1.0.0"
    val legacy_v13 = "androidx.legacy:legacy-support-v13:1.0.0"
}

object test {
    val junit = "junit:junit:4.12"
    val runner = "androidx.test:runner:1.2.0"
    val espresso = "androidx.test.espresso:espresso-core:3.2.0"
}

object kotlin{
    val plubin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
}*/


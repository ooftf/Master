# sample  
    language: android
    jdk: oraclejdk8
    sudo: false
    
    android:
      components:
        - platform-tools
        - tools
        - build-tools-28.0.2
        - android-28
        - add-on
        - extra
      licenses:
        - 'android-sdk-preview-license-52d11cd2'
        - 'android-sdk-license-.+'
        - 'google-gdk-license-.+'
    before_script:
      # Create and start emulator
      - echo no | android create avd --force -n test -t android-28 --abi armeabi-v7a
      - emulator -avd test -no-skin -no-audio -no-window &
      - android-wait-for-emulator
      - adb shell input keyevent 82 &
    # ./gradlew assembleRelease
    script: ./gradlew connectedAndroidTest
# 资料
 [travis](https://www.jianshu.com/p/2935b96d3059)
 
# 默认travis 会接受所有的 Licenses,但是你也可以定义自己的白名单
      licenses:
      - 'android-sdk-preview-license-52d11cd2'
      - 'android-sdk-license-.+'
      - 'google-gdk-license-.+'
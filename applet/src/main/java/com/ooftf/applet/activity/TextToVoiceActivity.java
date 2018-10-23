package com.ooftf.applet.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.ooftf.applet.R;
import com.ooftf.applet.R2;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.utils.JLog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
@Route(path = RouterPath.APPLET_ACTIVITY_TEXT_TO_VOICE)
public class TextToVoiceActivity extends BaseActivity {
    @BindView(R2.id.text)
    EditText textView;
    @BindView(R2.id.button)
    TextView button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_voice);
        ButterKnife.bind(this);
        initPermission();
        initVoice();
        button.setOnClickListener(v -> instance.speak(textView.getText().toString()));

    }

    SpeechSynthesizer instance;

    private void initVoice() {
        instance = SpeechSynthesizer.getInstance();
        instance.setContext(this);
        instance.setAppId("14523596");
        instance.setApiKey("kVa7qKU9WPCIQuCwxGGZ9TSm","yItIA3fGuEOavVs4c0s6SIpjmnRHjbn4");
        instance.auth(TtsMode.ONLINE);
        instance.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        instance.initTts(TtsMode.ONLINE);

    }
    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String permissions[] = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                //进入到这里代表没有权限.
            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }
}

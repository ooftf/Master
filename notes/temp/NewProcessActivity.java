package com.ooftf.docking.sample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.tencent.smtt.sdk.CookieManager;

/**
 * @author 99474
 */
public class NewProcessActivity extends BaseActivity {
    ServiceConnection serviceConnection;
    Messenger messenger;
    Messenger response = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("response", msg.getData().getString("123"));
            super.handleMessage(msg);

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_process);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(NewProcessActivity.this, MainActivity.class));
                try {
                    Message message = Message.obtain(null, 2);
                    Bundle bundle = new Bundle();
                    bundle.putString("123", "NewProcessActivity" + Process.myPid());
                    message.setData(bundle);
                    message.replyTo = response;
                    messenger.send(message);
                    //messenger.send(Message.obtain(null,2));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        getCookie();
        testSp();
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                messenger = new Messenger(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                serviceConnection = null;
                messenger = null;
            }
        };
        bindService(new Intent(this, MainService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
    }

    private void testSp() {
        String string = getSharedPreferences("TEST", Context.MODE_PRIVATE).getString("test", "no");
        Log.e("process", "sp:" + string);
    }

    private void getCookie() {
        CookieManager instance = CookieManager.getInstance();
        Log.e("process--", "" + instance.hashCode());
        instance.setAcceptCookie(true);
        String cookie = instance.getCookie("ooftf2.com");
        Log.e("process--", "cookie::" + cookie);
    }
}

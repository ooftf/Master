package com.ooftf.docking.sample;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/21 0021
 */
public class NewProcessService extends Service {
    final Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 2) {
                TestParcelable testParcelable = (TestParcelable) msg.obj;
                Toast.makeText(getApplicationContext(), Process.myPid() + "::" + testParcelable.getContent(), Toast.LENGTH_LONG).show();
                return;
            }
            super.handleMessage(msg);
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}

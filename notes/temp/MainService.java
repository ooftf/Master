package com.ooftf.docking.sample;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/21 0021
 */
public class MainService extends Service {
    final Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 2) {
                Toast.makeText(getApplicationContext(), Process.myPid() + "::" + msg.getData().get("123"), Toast.LENGTH_LONG).show();
                Message reply = Message.obtain();
                reply.what = 3;
                Bundle bundle = new Bundle();
                bundle.putString("123", "这是一条response");
                reply.setData(bundle);
                try {
                    msg.replyTo.send(reply);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
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

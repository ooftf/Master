package com.master.kit.testcase;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.os.Build;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class HongbaoAccessbilityService extends AccessibilityService {

    private String[] mFilter = new String[]{"恭喜发财", "领取红包", "微信红包"};

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                openNotification(event);
                break;

            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                openPacket(event);
                break;
            default:
        }

    }

    /**
     * 打开红包
     */
    private void openPacket(AccessibilityEvent event) {
        AccessibilityNodeInfo rootNodeInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            rootNodeInfo = event.getSource();
            if (rootNodeInfo == null) {
                return;
            }
            startClick(rootNodeInfo, "领取红包");
            startClick(rootNodeInfo, "開");
            startClick(rootNodeInfo, "查看红包");
        }
    }

    /**
     * 点击
     *
     * @param rootNodeInfo
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void startClick(AccessibilityNodeInfo rootNodeInfo, String text) {
        List<AccessibilityNodeInfo> nodeInfos = rootNodeInfo.findAccessibilityNodeInfosByText(text);
        if (nodeInfos == null || nodeInfos.isEmpty()) {
            return;
        }
        for (AccessibilityNodeInfo nodeInfo : nodeInfos) {
            click(nodeInfo);
        }
    }

    /**
     * 点击
     *
     * @param nodeInfo
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void click(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return;
        }
        boolean isClick = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        if (!isClick) {
            AccessibilityNodeInfo nodeInfoParent = nodeInfo.getParent();
            click(nodeInfoParent);
        }
    }

    private void openNotification(AccessibilityEvent event) {
        List<CharSequence> texts = event.getText();
        if (texts == null && texts.isEmpty()) {
            return;
        }
        for (CharSequence text : texts) {
            String content = text.toString();
            if (!TextUtils.isEmpty(content) && content.contains("微信红包")) {
                if (event.getParcelableData() != null && event.getParcelableData() instanceof Notification) {
                    Notification notification = (Notification) event.getParcelableData();
                    PendingIntent pendingIntent = notification.contentIntent;
                    try {
                        pendingIntent.send();
                    } catch (PendingIntent.CanceledException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}

package com.ooftf.service.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/12/12 0012
 */
public class TimeRuler {
    private static Map<String, Chain> map = new HashMap<>();

    public static void start(String key, String message) {
        map.put(key, new Chain(key, message));
    }

    public static void marker(String key, String message) {
        Chain info = map.get(key);
        if (info == null) {
            info = new Chain(key, message);
            map.put(key, info);
        } else {
            info.marker(message);
        }

    }

    public static void end(String key, String message) {
        Chain info = map.get(key);
        if (info == null) {
            new Chain(key, message);
        } else {
            info.marker(message);
            map.remove(key);
        }
    }


    public static class Chain {
        List<Marker> markers = new ArrayList<>();
        String key;

        Chain(String key, String message) {
            this.key = key;
            markers.add(new Marker(System.currentTimeMillis(), message));
            log();
        }

        void marker(String message) {
            markers.add(new Marker(System.currentTimeMillis(), message));
            log();
        }

        private void log() {
            StringBuilder ss = new StringBuilder();
            long lastTime = 0;
            for (int i = 0; i < markers.size(); i++) {
                Marker m = markers.get(i);
                if (i == 0) {
                    ss.append(m.message);
                } else {
                    ss.append(" === ").append((m.time - lastTime) / 1000f).append(" ===》 ").append(m.message);
                }
                lastTime = m.time;
            }
            if (markers.size() > 1) {
                ss.append("[").append((markers.get(markers.size() - 1).time - markers.get(0).time) / 1000f).append("]");
            }
            Log.e(key, ss.toString());
        }

    }

    public static class Marker {
        public Marker(long time, String message) {
            this.time = time;
            this.message = message;
        }

        long time;
        String message;
    }
}

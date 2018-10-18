package com.ooftf.service.engine.typer;

import android.content.Context;

import com.tencent.mmkv.MMKV;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/5 0005
 */
public class TyperFactory {
    private static ITyper typer;

    public static void init(Context context) {
        MMKV.initialize(context);
    }

    public static synchronized ITyper getDefault() {
        if (typer == null) {
            typer = new MMKVTyper();
        }
        return typer;
    }

}

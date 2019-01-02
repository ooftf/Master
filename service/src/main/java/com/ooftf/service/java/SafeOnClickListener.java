package com.ooftf.service.java;

import android.view.View;

public abstract class SafeOnClickListener implements View.OnClickListener {

    long last = 0L;

    @Override
    final public void onClick(View view) {
        long current = System.currentTimeMillis();
        if (current - last > safeMillis()) {
            safeOnClick(view);
            last = current;
        }
    }

    public abstract void safeOnClick(View view);

    public long safeMillis() {
        return 1000;
    }
}

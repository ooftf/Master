package com.ooftf.service.engine;

import android.view.View;

public abstract class SafeOnClickListenerJ implements View.OnClickListener {

    long last = 0L;

    @Override
    final public void onClick(View view) {
        long current = System.currentTimeMillis();
        if (current - last > safeMillis()) {
            safeOnClick(view);
            last = current;
        }
    }

    /**
     * 代替 onClick
     *
     * @param view
     */
    public abstract void safeOnClick(View view);

    public long safeMillis() {
        return 1000;
    }
}

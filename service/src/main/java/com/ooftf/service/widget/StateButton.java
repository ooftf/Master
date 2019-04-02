package com.ooftf.service.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.DragEvent;

import com.ooftf.service.R;

import androidx.appcompat.widget.AppCompatButton;

/**
 * @author master
 * @date 2016/2/26
 */
public class StateButton extends AppCompatButton {
    int selected = NO_ID;

    public StateButton(Context context) {
        super(context);
        init();
    }

    public StateButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        handleTypedArray(context, attrs);
        init();
    }

    public StateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handleTypedArray(context, attrs);
        init();
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.StateButton);
        selected = typedArray.getResourceId(
                R.styleable.StateButton_pressed, NO_ID);
        typedArray.recycle();
    }

    private Drawable initialBackground;

    private void init() {
        setOnDragListener((v, event) -> {
            if (selected != NO_ID) {
                if (event.getAction() == DragEvent.ACTION_DRAG_STARTED) {
                    initialBackground = getBackground();
                    setBackgroundResource(selected);
                } else if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                    setBackground(initialBackground);
                    initialBackground = null;
                }
            }
            return false;
        });
    }
}

package com.ooftf.widget.self;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.ooftf.service.utils.CanvasUtil;

/**
 *
 * Created by master on 2017/6/22 0022.
 *
 */

@SuppressLint("AppCompatCustomView")
public class PinEditText extends EditText {
    private static final String XML_NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
    int w;
    int h;
    Paint strokePaint;
    PointF[] point;
    float contentW;
    int maxLength;
    public PinEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PinEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        maxLength = attrs.getAttributeIntValue(XML_NAMESPACE_ANDROID, "maxLength", 4);
        initStrokePaint();
        setBackgroundDrawable(null);
        setCursorVisible(false);
        setTextIsSelectable(false);
        setLongClickable(false);
        /**
         * 将光标固定在文本结尾
         */
        setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    setSelection(length());
                }
            }
        });
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelection(length());
            }

        });
        /**
         * 禁止长按复制粘贴功能
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            super.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            });
        }

    }
    @Override
    public void setCustomSelectionActionModeCallback(ActionMode.Callback actionModeCallback) {
        throw new RuntimeException("setCustomSelectionActionModeCallback() not supported.");
    }
    private void initStrokePaint() {
        getPaint().setColor(getCurrentTextColor());
        strokePaint = new Paint();
        strokePaint.setAntiAlias(true);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(getPaint().getColor());
        strokePaint.setStrokeWidth(getPaint().getStrokeWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
        strokePaint.setStrokeWidth(h / 50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        calculatePoint();
        drawTextByCenter(canvas);
        drawRoundRect(canvas);
        drawVerticalLine(canvas);
    }

    private void drawRoundRect(Canvas canvas) {
        float strokeWidthHalf = strokePaint.getStrokeWidth() / 2;
        RectF rectF = new RectF(0 + strokeWidthHalf, 0 + strokeWidthHalf, w - strokeWidthHalf, h - strokeWidthHalf);
        canvas.drawRoundRect(rectF, h / 10, h / 10, strokePaint);
    }

    private void drawVerticalLine(Canvas canvas) {
        for (int i = 0; i < maxLength - 1; i++) {
            canvas.drawLine(point[i].x + contentW / 2 + strokePaint.getStrokeWidth() / 2, 0, point[i].x + contentW / 2 + strokePaint.getStrokeWidth() / 2, h, strokePaint);
        }
    }

    private void drawTextByCenter(Canvas canvas) {
        String text = getText().toString();
        for (int i = 0; i < text.length(); i++) {
            CanvasUtil.INSTANCE.drawTextForCenter(String.valueOf(text.charAt(i)), point[i].x, point[i].y, canvas, getPaint());
        }
    }

    void calculatePoint() {
        contentW = (w - strokePaint.getStrokeWidth() * (maxLength + 1)) / maxLength;
        point = new PointF[maxLength];
        for (int i = 0; i < maxLength; i++) {
            point[i] = new PointF((strokePaint.getStrokeWidth() + contentW / 2) + i * (strokePaint.getStrokeWidth() + contentW), h / 2);
        }
    }
}

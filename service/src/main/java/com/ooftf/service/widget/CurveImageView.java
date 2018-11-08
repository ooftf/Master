package com.ooftf.service.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import com.ooftf.service.utils.DensityUtil;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/30 0030
 */
public class CurveImageView extends android.support.v7.widget.AppCompatImageView {


    Path path;
    public CurveImageView(Context context) {
        super(context);
        init();
    }


    public CurveImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CurveImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float edge = DensityUtil.INSTANCE.dip2px(getContext(), 40);
        float bicHeight = DensityUtil.INSTANCE.dip2px(getContext(), 40);
        path = new Path();
        path.lineTo(0, getHeight() - bicHeight);
        path.cubicTo(edge, getHeight()- bicHeight/2,
                getWidth() - edge, getHeight() - bicHeight/2,
                getWidth(), getHeight());
        path.lineTo(getWidth(), 0);
        path.lineTo(0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}


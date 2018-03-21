package com.master.kit.widget.indicatortextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ooftf.service.utils.DensityUtil;

@SuppressLint("AppCompatCustomView")
public class ProgressTextView extends TextView {

    /**
     * 当前进度  取值0-100  有可能超过100
     */
    float progress;
    Paint paint;
    float underlineWidth;

	public ProgressTextView(Context context) {
		super(context);
		init();
	}

	public ProgressTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public ProgressTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
        underlineWidth = DensityUtil.INSTANCE.dip2px(getContext(), 2);
        paint = new Paint();
		progress = 0;
	}
	// private Handler handler;

	/**
	 * Create the shader draw the wave with current color for a background
	 * repeat the bitmap horizontally, and clamp colors vertically
	 */
	private void createShader() {
		Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(createBitmap);
		if (progress <= 100) {
			paint.setColor(Color.RED);
			canvas.drawRect(0, 0, getWidth() * progress / 100, getHeight(), paint);
			paint.setColor(getCurrentTextColor());
			canvas.drawRect(getWidth() * progress / 100, 0, getWidth(), getHeight(), paint);
		} else {
			float tem = progress - 100f;
			paint.setColor(getCurrentTextColor());
			canvas.drawRect(0, 0, getWidth() * tem / 100, getHeight(), paint);
			paint.setColor(Color.RED);
			canvas.drawRect(getWidth() * tem / 100, 0, getWidth(), getHeight(), paint);
		}
		getPaint().setShader(new BitmapShader(createBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
	}

	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
		invalidate();
	}

	public float getUnderlineWidth() {
        return DensityUtil.INSTANCE.px2dip(getContext(), underlineWidth);
    }

	public void setUnderlineWidth(float underlineWidthDP) {
        this.underlineWidth = DensityUtil.INSTANCE.dip2px(getContext(), underlineWidthDP);
    }

	@Override
	protected void onDraw(Canvas canvas) {

		createShader();

		super.onDraw(canvas);
		paint.setColor(Color.RED);
		if (progress <= 100) {
			canvas.drawRect(0, getHeight() - underlineWidth, getWidth() * progress / 100, getHeight(), paint);
		} else {
			float tem = progress - 100f;
			canvas.drawRect(getWidth() * tem / 100, getHeight() - underlineWidth, getWidth(), getHeight(), paint);
		}

		// invalidate();
	}
}

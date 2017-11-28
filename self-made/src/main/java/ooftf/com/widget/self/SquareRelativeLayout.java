package ooftf.com.widget.self;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by 99474 on 2017/10/27 0027.
 */

public class SquareRelativeLayout extends RelativeLayout {
    public SquareRelativeLayout(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public SquareRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareRelativeLayout(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int withdMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightdMode = MeasureSpec.getMode(heightMeasureSpec);
        int withdSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightdSize = MeasureSpec.getSize(heightMeasureSpec);
        if(withdMode == MeasureSpec.EXACTLY||withdMode == MeasureSpec.AT_MOST){
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        }else if(heightdMode == MeasureSpec.EXACTLY||heightdMode == MeasureSpec.AT_MOST){
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}

package com.master.kit.widget.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import static com.master.kit.widget.banner.VisualCheatingOnPageChangeListener.CHEAT_VIEW;

/**
 * Created by master on 2017/1/23.
 */

public class BannerPagerAdapter extends PagerAdapter {
    DisplayImageCallback callback;
    private OnItemClickListener mListener;
    private List<String> mUris;
    private Context mContext;
    /**
     * 复用集合,用于回收
     */
    private List<View> recycleViews;



    BannerPagerAdapter(List<String> uris,Context context, DisplayImageCallback callback) {
        this.callback = callback;
        recycleViews = new ArrayList<>();
        mUris =uris;
        mContext = context;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        return mUris.size() + CHEAT_VIEW;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int srcPosition) {
        final int position = srcPosition % mUris.size();
        Log.e("生成", position + "");
        ImageView imageView;
        imageView = (ImageView) getView();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick((ImageView) v, mUris.get(position), position);
                }
            }
        });
        callback.displayImage(mUris.get(position), imageView);
        container.addView(imageView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.e("移除", position + "");
        container.removeView((View) object);
        recycleViews.add((View) object);
    }
    /**
     * 获取Item视图，优先复用
     *
     * @return
     */
    public View getView() {
        View view;
        if (recycleViews.size() == 0) {//不可复用
            view = new ImageView(mContext);
            ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_XY);
        } else {//可复用
            view = recycleViews.get(0);
            recycleViews.remove(view);
        }
        return view;
    }
    /**
     * ViewPage Item的点击事件，其实是在每个Item中设置单独的点击事件实现的
     */
    public interface OnItemClickListener {
        void onItemClick(ImageView v, String data, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
    /**
     *  Url display ImageView 的操作以回调的形式交给使用者实现
     */
    public interface DisplayImageCallback {
        void displayImage(String url, ImageView view);
    }

    @Override
    public void startUpdate(ViewGroup container) {
        Log.e("startUpdate","startUpdate");
        super.startUpdate(container);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        Log.e("finishUpdate","finishUpdate");
        super.finishUpdate(container);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Log.e("setPrimaryItem","setPrimaryItem");
        super.setPrimaryItem(container, position, object);
    }
}

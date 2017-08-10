package com.ooftf.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

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
    private SparseArray<View> recycleViews;


    BannerPagerAdapter(List<String> uris,Context context, DisplayImageCallback callback) {
        this.callback = callback;
        recycleViews = new SparseArray<>();
        mUris =uris;
        mContext = context;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = convertPosition(position);
        ImageView imageView;
        imageView = (ImageView) getView(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.e("移除", position + "");
        container.removeView((View) object);
        recycleViews.put(convertPosition(position),(View) object);
    }
    /**
     * 获取Item视图，优先复用
     *
     * @return
     */
    public View getView(final int position) {
        ImageView view = (ImageView) recycleViews.get(position);
        if (view == null) {//不可复用
            Log.e("生成", position + "");
            view = new ImageView(mContext);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick((ImageView) v, mUris.get(position), position);
                    }
                }
            });
            callback.displayImage(mUris.get(position), view);
            view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        } else {//可复用
            Log.e("复用", position + "");
            recycleViews.remove(position);
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
    int convertPosition(int position){
        return position % mUris.size();
    }
}

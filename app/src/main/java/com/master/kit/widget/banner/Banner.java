package com.master.kit.widget.banner;

import java.util.ArrayList;
import java.util.List;

import com.master.kit.R;
import com.master.kit.utils.DensityUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;

public class Banner extends RelativeLayout {



	private Context mContext;
	private CircleIndicator mCiBanner;
	private ViewPager mVpBanner;
	private OnItemClickListener mListener;
	/**
	 * 默认宽高比
	 */
	private final float defaultProportion = 300/640f;

	@SuppressLint("NewApi")
	public Banner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context);
	}

	public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public Banner(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public Banner(Context context) {
		super(context);
		init(context);
	}

	void init(Context context) {
		mContext = context;

		View.inflate(mContext, R.layout.widget_banner, this);
		mCiBanner = (CircleIndicator) findViewById(R.id.ci_banner);
		mVpBanner = (ViewPager) findViewById(R.id.vp_banner);
		setProportion(defaultProportion);

	}
	public void setProportion(float heightVSwidth){
		mVpBanner.getLayoutParams().width = DensityUtil.getScreenWidthPixels(getContext());
		mVpBanner.getLayoutParams().height = (int)(DensityUtil.getScreenWidthPixels(getContext())*heightVSwidth);
	}

	/**
	 * 优先抢到触摸事件的处理权限
	 * @param ev
	 * @return
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if(ev.getAction() == MotionEvent.ACTION_DOWN){
			requestDisallowInterceptTouchEvent(true);
			stopCycle();
		}
		if(ev.getAction() == MotionEvent.ACTION_UP||ev.getAction() == MotionEvent.ACTION_CANCEL){
			startCycle();
		}
		return super.dispatchTouchEvent(ev);
	}

	private List<String> uris;
	public void setUris(final List<String> urls, final DisplayImageCallback callback) {

		if (urls == null || urls.size() < 1) {
			mVpBanner.setAdapter(null);
			mCiBanner.setVisibility(View.INVISIBLE);
			return;
		}
		uris = new ArrayList<>(urls);
		mVpBanner.setAdapter(new BannerPagerAdapter(callback));
		mCiBanner.setViewPager(mVpBanner, uris.size());
		mCiBanner.setVisibility(View.VISIBLE);
	}
	public interface DisplayImageCallback {
		void displayImage(String url,ImageView view);
	}

	public void startCycle() {
		if(uris!=null&&uris.size()>1){
			mCiBanner.startCycle();
		}

	}

	public void stopCycle() {
		mCiBanner.stopCycle();
	}

	private class BannerPagerAdapter extends PagerAdapter{
		DisplayImageCallback callback;
		/**
		 * 复用集合,用于回收
		 */
		List<View> recycleViews;
		BannerPagerAdapter(DisplayImageCallback callback){
			this.callback = callback;
			recycleViews = new ArrayList<>();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			return uris.size()+2;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int srcPosition) {
			final int position = srcPosition % uris.size();
			ImageView imageView;
			imageView = (ImageView) getView();
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mListener != null) {
						mListener.onItemClick((ImageView) v, uris.get(position), position);
					}
				}
			});
			callback.displayImage(uris.get(position),imageView);
			container.addView(imageView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			return imageView;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
			recycleViews.add((View) object);
		}

		/**
		 * 获取Item视图，优先复用
		 * @return
         */
		public View getView() {
			View view;
			if(recycleViews.size()==0){//不可复用
				view = new ImageView(mContext);
				((ImageView)view).setScaleType(ScaleType.FIT_XY);
			}else{//可复用
				view = recycleViews.get(0);
				recycleViews.remove(view);
			}
			return view;
		}

		@Override
		public void finishUpdate(ViewGroup container) {
			int position = mVpBanner.getCurrentItem();
			/**
			 *  第五这里获得当前的positon然后对其setCurrentItem进行变换
			 *  这里设置当position=0时把position设置为图片列表的最大值
			 *  是为了position=0时左滑显示最后一张，我举个例子这里ImageSize是5
			 *  当position==0时设置为5，左滑就是position=4，也就是第五张图片，
			 *
			 *  if (position == (ImageSize+2) - 1)
			 *  这个判断 (ImageSize+2)这个是给viewpager设置的页面数，这里是7
			 *  当position==7-1=6时，这时viewpager就滑到头了，所以把currentItem设置为1
			 *  这里设置为1还是为了能够左滑，这时左滑position=0又执行了第一个判断又设置为5，
			 *  这样就实现了无限轮播的效果
			 *  setCurrentItem(position,false);
			 *  这里第二个参数false是消除viewpager设置item时的滑动动画，不理解的去掉它运行下就知道啥意思了
			 *
			 */
			if (position == 0) {
				position = uris.size();
				mVpBanner.setCurrentItem(position,false);
			} else if (position == getCount() - 1) {
				position = 1;
				mVpBanner.setCurrentItem(position,false);
			}
		}
	}
	public interface OnItemClickListener {
		public void onItemClick(ImageView v, String data, int position);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.mListener = listener;
	}
}

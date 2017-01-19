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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

public class Banner extends RelativeLayout {



	private Context mContext;
	private CircleIndicator mCiBanner;
	private ViewPager mVpBanner;
	private OnItemClickListener mListener;
	/**
	 * 默认宽高比
	 */
	private final float defaultProportion = 300/640f;
	/**
	 * 复用集合
	 */
	List<View> recycleViews;
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
		recycleViews = new ArrayList<>();
		View.inflate(mContext, R.layout.widget_banner, this);
		mCiBanner = (CircleIndicator) findViewById(R.id.ci_banner);
		mVpBanner = (ViewPager) findViewById(R.id.vp_banner);
		setProportion(defaultProportion);

	}
	public void setProportion(float heightVSwidth){
		mVpBanner.getLayoutParams().width = DensityUtil.getScreenWidthPixels(getContext());
		mVpBanner.getLayoutParams().height = (int)(DensityUtil.getScreenWidthPixels(getContext())*heightVSwidth);
		/*mVpBanner.setLayoutParams(new RelativeLayout.LayoutParams(DensityUtil.getScreenWidthPixels(getContext()),(int)(DensityUtil.getScreenWidthPixels(getContext())*heightVSwidth)));*/
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
	public void setUris(final List<String> urisl, final DisplayImageCallback callback) {

		if (urisl == null || urisl.size() < 1) {
			mVpBanner.setAdapter(null);
			mCiBanner.setVisibility(View.INVISIBLE);
			return;
		}
		uris = new ArrayList<String>(urisl);
		//this.uris.addAll(urisl);
		mVpBanner.setAdapter(new PagerAdapter() {
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return Integer.MAX_VALUE;
			}
			@Override
			public Object instantiateItem(ViewGroup container, int srcPosition) {
				final int position = srcPosition % uris.size();
				ImageView imageView;
				if(recycleViews.size()==0){
					imageView = new ImageView(mContext);
					imageView.setScaleType(ScaleType.FIT_XY);
				}else{
					imageView = (ImageView) recycleViews.get(0);
					recycleViews.remove(imageView);
				}
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
		});
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

	public interface OnItemClickListener {
		public void onItemClick(ImageView v, String data, int position);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.mListener = listener;
	}
}

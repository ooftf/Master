package com.ooftf.widget.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.SharedElementCallback;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.engine.GlideApp;
import com.ooftf.widget.R;
import com.ooftf.widget.databinding.ActivityImagePreviewBinding;

import java.util.List;
import java.util.Map;

@Route(path = RouterPath.Widget.Activity.IMAGE_PREVIEW)
public class ImagePreviewActivity extends AppCompatActivity {
    @Autowired(name = "data")
    ImagePreviewBean bean;
    ActivityImagePreviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(Window.FEATURE_CONTENT_TRANSITIONS);
            postponeEnterTransition();
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_preview);
        setupViewPager();
    }

    private void setupViewPager() {
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    binding.viewPager.setTransitionName(bean.list.get(position).transitionName);
                }
            }
        });
        binding.viewPager.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ImageView imageView = new ImageView(parent.getContext());
                imageView.setLayoutParams(new ViewPager2.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return new RecyclerView.ViewHolder(imageView) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && position == 0) {
                    holder.itemView.setTransitionName(bean.list.get(position).transitionName);
                }
                //GlideApp.get(getApplication()).
                GlideApp.with(getApplication()).load(bean.list.get(position).url).listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            startPostponedEnterTransition();
                        }
                        return false;
                    }
                }).into((ImageView) holder.itemView);
                //ImageLoaderFactory.INSTANCE.createInstance().display(ImagePreviewActivity.this, bean.list.get(position), (ImageView) holder.itemView);
            }

            @Override
            public int getItemCount() {
                return bean.list.size();
            }
        });

        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                super.onMapSharedElements(names, sharedElements);
                String name = bean.list.get(binding.viewPager.getCurrentItem()).transitionName;
                sharedElements.clear();
                names.clear();
                names.add(name);
                sharedElements.put(name, binding.viewPager);
            }
        });
        binding.viewPager.setCurrentItem(bean.position, false);
    }

    @Override
    public void supportFinishAfterTransition() {
        Intent data = new Intent();
        data.putExtra("data", binding.viewPager.getCurrentItem());
        setResult(RESULT_OK, data);
        super.supportFinishAfterTransition();
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
        super.onBackPressed();
    }

    public static class ImagePreviewBean {
        public int position;
        public List<Bean> list;


        public ImagePreviewBean() {
        }

        public ImagePreviewBean(int position, List<Bean> list) {
            this.position = position;
            this.list = list;
        }

        public static class Bean {
            public Bean() {
            }

            public Bean(String url, String transitionName) {
                this.url = url;
                this.transitionName = transitionName;
            }

            public String url;
            public String transitionName;
        }
    }
}

package com.ooftf.widget.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.RectF;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.chrisbanes.photoview.PhotoView;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.engine.imageloader.ImageLoaderFactory;
import com.ooftf.widget.R;
import com.ooftf.widget.databinding.ActivityImagePreviewBinding;

import java.util.ArrayList;
import java.util.List;
@Route(path = RouterPath.Widget.Activity.IMAGE_PREVIEW)
public class ImagePreviewActivity extends AppCompatActivity {
    ImagePreviewBean bean = new ImagePreviewBean(){
        {
            list = new ArrayList<String>(){
                {
                    add("http://img5.imgtn.bdimg.com/it/u=1511658505,1279373164&fm=26&gp=0.jpg");
                    add("http://img5.imgtn.bdimg.com/it/u=1511658505,1279373164&fm=26&gp=0.jpg");
                    add("http://img5.imgtn.bdimg.com/it/u=1511658505,1279373164&fm=26&gp=0.jpg");
                    add("http://img5.imgtn.bdimg.com/it/u=1511658505,1279373164&fm=26&gp=0.jpg");
                }
            };
        }
    };
    ActivityImagePreviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_preview);
        binding.viewPager.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ImageView imageView = new ImageView(parent.getContext());
                imageView.setLayoutParams(new ViewPager2.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                return new RecyclerView.ViewHolder(imageView) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ImageLoaderFactory.INSTANCE.createInstance().display(ImagePreviewActivity.this,bean.list.get(position), (ImageView) holder.itemView);
            }

            @Override
            public int getItemCount() {
                return bean.list.size();
            }
        });
      /*  binding.viewPager.getLayoutParams().width = 100;
        binding.viewPager.getLayoutParams().height = 100;*/
        TransitionManager.beginDelayedTransition(binding.viewPager, new ChangeBounds());
        //TransitionManager.
      /*  binding.viewPager.getLayoutParams().width = 1000;
        binding.viewPager.getLayoutParams().height = 1000;*/
    }


    public static class ImagePreviewBean {
        public RectF startRect = new RectF();
        List<String> list;
    }
}

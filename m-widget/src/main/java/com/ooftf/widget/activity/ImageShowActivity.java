package com.ooftf.widget.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.SharedElementCallback;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.ooftf.arch.frame.mvvm.activity.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.engine.GlideApp;
import com.ooftf.service.interfaces.click.OnClickListener2;
import com.ooftf.widget.BR;
import com.ooftf.widget.R;
import com.ooftf.widget.databinding.ActivityImageShowBinding;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * @author 99474
 */
@Route(path = RouterPath.Widget.Activity.IMAGE_SHOW)
public class ImageShowActivity extends BaseActivity {
    ObservableList<ImagePreviewActivity.ImagePreviewBean.Bean> items = new ObservableArrayList<>();
    OnClickListener2<ImagePreviewActivity.ImagePreviewBean.Bean> clickListener = (view, data) -> {

        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(ImageShowActivity.this, view, data.transitionName);
        //startActivity(new Intent(ImageShowActivity.this, ImagePreviewActivity.class), transitionActivityOptions.toBundle());
        ARouter.getInstance().build(RouterPath.Widget.Activity.IMAGE_PREVIEW).withObject("data", new ImagePreviewActivity.ImagePreviewBean(items.indexOf(data), items)).withOptionsCompat(transitionActivityOptions).navigation(ImageShowActivity.this);
    };
    ItemBinding<ImagePreviewActivity.ImagePreviewBean.Bean> itemBinding = ItemBinding.<ImagePreviewActivity.ImagePreviewBean.Bean>of(BR.item, R.layout.item_image_show).bindExtra(BR.clickListener, clickListener);
    ActivityImageShowBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_show);
        binding.setItemBinding(itemBinding);
        binding.setItems(items);
        items.add(new ImagePreviewActivity.ImagePreviewBean.Bean("http://img5.imgtn.bdimg.com/it/u=1511658505,1279373164&fm=26&gp=0.jpg", "0"));
        items.add(new ImagePreviewActivity.ImagePreviewBean.Bean("http://img5.imgtn.bdimg.com/it/u=1511658505,1279373164&fm=26&gp=0.jpg", "1"));
        items.add(new ImagePreviewActivity.ImagePreviewBean.Bean("http://img5.imgtn.bdimg.com/it/u=1511658505,1279373164&fm=26&gp=0.jpg", "2"));
        items.add(new ImagePreviewActivity.ImagePreviewBean.Bean("http://img5.imgtn.bdimg.com/it/u=1511658505,1279373164&fm=26&gp=0.jpg", "3"));
        items.add(new ImagePreviewActivity.ImagePreviewBean.Bean("http://img5.imgtn.bdimg.com/it/u=1511658505,1279373164&fm=26&gp=0.jpg", "4"));
        items.add(new ImagePreviewActivity.ImagePreviewBean.Bean("http://img5.imgtn.bdimg.com/it/u=1511658505,1279373164&fm=26&gp=0.jpg", "5"));

        setExitSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                if (backIndex >= 0) {
                    sharedElements.clear();
                    names.clear();
                    names.add(items.get(backIndex).transitionName);
                    View itemView = binding.recyclerView.getLayoutManager().findViewByPosition(backIndex);
                    //注意这里第二个参数，如果防止是的条目的item则动画不自然。放置对应的imageView则完美
                    sharedElements.put(items.get(backIndex).transitionName, itemView);
                    backIndex = -1;
                }
            }
        });
    }

    int backIndex = -1;

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        backIndex = data.getIntExtra("data", -1);
    }


}

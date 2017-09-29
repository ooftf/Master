package tf.oof.com.service.engine.image_loader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by master on 2017/1/22.
 */

public class GlideImp implements IImageLoader {
    @Override
    public void init(Context context) {

    }

    @Override
    public void display(Context context, String url, ImageView view) {
        //Glide.with(context).load(url).into(view);
    }

    @Override
    public void display(Context context, String url, ImageView view, ImageLoaderConfig config) {

    }
}

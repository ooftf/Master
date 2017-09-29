package tf.oof.com.service.engine.image_loader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by master on 2017/1/22.
 */

public interface IImageLoader {
     void init(Context context);
     void display(Context context, String url, ImageView view);
     void display(Context context, String url, ImageView view,ImageLoaderConfig config);
}

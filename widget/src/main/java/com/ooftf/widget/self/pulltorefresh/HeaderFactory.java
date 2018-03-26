package com.ooftf.widget.self.pulltorefresh;

import android.content.Context;

/**
 * Created by master on 2017/2/8.
 */

public class HeaderFactory {
   public static APullToRefreshHeader createHeader(Context context){
        return new PullToRefreshHeader(context);
   }
}

package tf.oof.com.service.engine

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v4.widget.NestedScrollView
import android.view.View

/**
 * Created by 99474 on 2017/11/16 0016.
 */

object PerspectiveCompact{
    fun bindView(scrollerView:NestedScrollView,cover: View){
        cover.post {
            setBackgroundByScrollY(scrollerView,cover,0)
        }
        scrollerView.setOnScrollChangeListener{
            v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int->
            setBackgroundByScrollY(scrollerView,cover,scrollY)
        }
    }
    //根据scrollView 的scrollY设置背景图片
    private fun setBackgroundByScrollY(scrollerView:NestedScrollView,cover: View,scrollY:Int) {
        val root = scrollerView.getChildAt(0);
        val background = root.background as BitmapDrawable
        val bitmap = background.bitmap
        //计算图片和scrollview 内部高度的比例
        val proportion = bitmap.height.toFloat() / root.height
        //根据比例求图片开始位置
        val startY = (scrollY * proportion).toInt()
        //根据比例求图片高度
        val height = (cover.height * proportion).toInt()
        val drawable = BitmapDrawable(Bitmap.createBitmap(bitmap, 0, startY, bitmap.width, height))
        cover.setBackgroundDrawable(drawable)
    }
}

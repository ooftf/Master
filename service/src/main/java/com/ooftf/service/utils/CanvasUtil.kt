package com.ooftf.service.utils

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi

/**
 * Created by master on 2016/4/8.
 */
object CanvasUtil {
    /**
     * 以x和y为中心绘画文字内容，普通drawText中x,y为文字的左下角；
     * @param text
     * @param x
     * @param y
     * @param canvas
     * @param paint
     */
    fun drawTextForCenter(text: String, x: Float, y: Float, canvas: Canvas, paint: Paint) {
        val rect = Rect()
        paint.getTextBounds("A", 0, 1, rect)
        val halfH = (rect.height() / 2).toFloat()
        val halfW = paint.measureText(text) / 2
        canvas.drawText(text, x - halfW, y + halfH, paint)
    }

    fun getBitmapFromVectorId(context: Context, vectorId: Int): Bitmap {
        var bitmap: Bitmap
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            bitmap = bitmapAtLeast24(context, vectorId)
        } else {
            bitmap = BitmapFactory.decodeResource(context.resources, vectorId)
        }
        return bitmap
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun bitmapAtLeast24(context: Context, vectorDrawableId: Int): Bitmap {
        var bitmap: Bitmap
        val vectorDrawable = context.getDrawable(vectorDrawableId)
        bitmap = Bitmap.createBitmap(vectorDrawable!!.intrinsicWidth,
                vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return bitmap
    }

    fun drawBitmapForCenter(canvas: Canvas, bitmap: Bitmap, paint: Paint, cx: Float, cy: Float, width: Float, height: Float) {
        var matrix = Matrix()
        var scaleWidth = width / bitmap.width
        var scaleHeight = height / bitmap.height
        matrix.postScale(scaleWidth, scaleHeight)
        matrix.postTranslate(cx - width / 2, cy - height / 2)
        canvas.drawBitmap(bitmap, matrix, paint)
    }
}

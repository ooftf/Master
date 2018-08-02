package com.ooftf.master.other.engine;

import android.content.Context;
import android.graphics.Point;

import com.ooftf.master.other.R;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;

import java.util.EnumSet;
import java.util.Set;

public class MiniSizeFilter extends Filter {
    private int mMinWidth;
    private int mMinHeight;
    private int mMaxSize;
    public MiniSizeFilter(int minWidth, int minHeight, int maxSizeInBytes) {
        mMinWidth = minWidth;
        mMinHeight = minHeight;
        mMaxSize = maxSizeInBytes;
    }

    /**
     * 约束的图片类型
     * @return
     */
    @Override
    protected Set<MimeType> constraintTypes() {
        return EnumSet.of(MimeType.JPEG, MimeType.PNG, MimeType.BMP, MimeType.WEBP);
    }

    @Override
    public IncapableCause filter(Context context, Item item) {
        if (!needFiltering(context, item)){
            return null;
        }

        Point size = PhotoMetadataUtils.getBitmapBound(context.getContentResolver(), item.getContentUri());
        if (size.x < mMinWidth || size.y < mMinHeight || item.size > mMaxSize) {
            // IncapableCause.TOAST 表示 Toast 提示，它有三个选择：{TOAST, DIALOG, NONE}
            return new IncapableCause(IncapableCause.TOAST, context.getString(R.string.image_size_to_small, mMinWidth,
                    String.valueOf(PhotoMetadataUtils.getSizeInMB(mMaxSize))));
        }
        return null;
    }
}

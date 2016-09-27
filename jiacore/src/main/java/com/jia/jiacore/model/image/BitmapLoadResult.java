package com.jia.jiacore.model.image;

import android.graphics.Bitmap;

/**
 * Description: 图片加载为Bitmap结果对象
 *
 * @author liuranchao
 * @date 16/9/1 下午5:53
 */
public class BitmapLoadResult {

    private Bitmap mBitmap;

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }
}

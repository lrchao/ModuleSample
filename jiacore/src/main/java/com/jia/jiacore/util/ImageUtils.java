package com.jia.jiacore.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.jia.jiacore.IBaseApplication;
import com.jia.jiacore.model.image.BitmapLoadResult;
import com.jia.jiacore.model.image.ImageLoadResult;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import java.io.File;

/**
 * Description: 图片加载的工具类
 * 使用https://github.com/square/picasso/tree/master/picasso
 *
 * @author liuranchao
 * @date 16/9/1 下午5:16
 */
public final class ImageUtils {

    private static ImageUtils sInstance;

    private ImageUtils() {
        try {
            Picasso.Builder builder = new Picasso.Builder(IBaseApplication.getApplication());
            Picasso picasso = builder.build();
            picasso.setIndicatorsEnabled(false);
            Picasso.setSingletonInstance(picasso);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static ImageUtils getInstance() {
        synchronized (ImageUtils.class) {
            if (sInstance == null) {
                sInstance = new ImageUtils();
            }
        }
        return sInstance;
    }

    /**
     * 加载图片
     *
     * @param imgUrl      图片的网络地址
     * @param imageView   ImageView
     * @param placeHolder 展位图片
     */
    public void display(String imgUrl, ImageView imageView, @DrawableRes int placeHolder, final OnImageLoadListener callback) {

        try {
            Picasso.with(IBaseApplication.getApplication())
                    .load(imgUrl)
                    .fit()
                    .centerCrop()
                    .placeholder(placeHolder)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            if (callback != null) {
                                callback.onSuccess(new ImageLoadResult());
                            }
                        }

                        @Override
                        public void onError() {
                            if (callback != null) {
                                callback.onError(new ImageLoadResult());
                            }
                        }
                    });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示文件
     * CenterCrop()是一种尺度图像的裁剪技术,填补了ImageView的要求范围,然后修剪其余的范围。ImageView将被完全填满,但整个图像可能不会显示。
     * CenterInside()是一种尺度图像的裁剪技术,这样两个尺寸等于或小于请求的ImageView的界限。图像将显示完全,但可能不会填满整个ImageView
     */
    public void display(File file, ImageView imageView, @DrawableRes int placeHolder) {

        try {
            boolean isFileExist = FileUtils.isExist(file);
            if (isFileExist) {
                RequestCreator creator = Picasso.with(IBaseApplication.getApplication())
                        .load(file)
                        .fit()
                        .centerInside()
                        .placeholder(placeHolder);

                creator.into(imageView);
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * @param resId     Drawable resource id
     * @param imageView ImageView
     */
    public void display(@DrawableRes int resId, ImageView imageView) {
        try {
            Picasso.with(IBaseApplication.getApplication()).load(resId).fit().centerCrop().into(imageView);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载Bitmap
     *
     * @param url      net image url
     * @param listener OnBitmapLoadListener
     */
    public void loadBitmap(String url, final OnBitmapLoadListener listener) {

        Picasso.with(IBaseApplication.getApplication()).load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if (listener != null) {
                    BitmapLoadResult result = new BitmapLoadResult();
                    result.setBitmap(bitmap);
                    listener.onBitmapLoaded(result);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                if (listener != null) {
                    listener.onBitmapFailed(new BitmapLoadResult());
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    /**
     * 加载图片 结果接口
     */
    public interface OnImageLoadListener {
        void onSuccess(ImageLoadResult result);

        void onError(ImageLoadResult result);
    }

    /**
     * 加载Bitmap接口
     */
    public interface OnBitmapLoadListener {
        void onBitmapLoaded(BitmapLoadResult result);

        void onBitmapFailed(BitmapLoadResult result);

    }
}

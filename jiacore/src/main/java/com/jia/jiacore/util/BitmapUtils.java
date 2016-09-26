package com.jia.jiacore.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Description: Bitmap工具类
 *
 * @author lrc19860926@gmail.com
 * @date 16/4/18 上午11:04
 */
public final class BitmapUtils {

    private BitmapUtils() {
    }

    /**
     * 压缩bitmap到文件
     *
     * @param bmp  要压缩的bitmap
     * @param file 压缩后写的文件
     * @param size 最大的大小KB
     * @return 是否压缩完成
     */
    public static boolean compressBmpToFile(Bitmap bmp, File file, int size) {

        int offset = 5;

        boolean isOK = false;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > size) {
            baos.reset();
            options -= offset;
            if (options > 0) {
                bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
            } else {
                options += offset;
                bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
                break;
            }
        }

        recycle(bmp);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();

            isOK = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isOK;
    }

    /**
     * 获得图片文件的bitmap, 根据设备
     *
     * @param filePath  图片文件路径
     * @param reqWidth  期望的宽度
     * @param reqHeight 期望的高度
     */
    public static Bitmap decodeSampledBitmapFromFile(String filePath, int reqWidth, int reqHeight) {
        Bitmap bitmap = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 计算Sample size
     *
     * @param options   BitmapFactory.Options
     * @param reqWidth  期望的宽度
     * @param reqHeight 期望的高度
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
            final float totalPixels = width * height;
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;
            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }

    /**
     * bitmap可不可用
     *
     * @param b Bitmap
     */
    public static boolean isAvailableBitmap(Bitmap b) {
        return (b != null && !b.isRecycled());
    }

    /**
     * 释放图片
     *
     * @param bitmap Bitmap
     */
    public static void recycle(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }
}

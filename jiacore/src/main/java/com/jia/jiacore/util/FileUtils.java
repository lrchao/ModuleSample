package com.jia.jiacore.util;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jia.jiacore.constant.IBaseConstants;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Description: 文件操作的工具类
 *
 * @author lrc19860926@gmail.com
 * @date 16/3/21 下午4:41
 */
public final class FileUtils {


    private FileUtils() {
    }

    //=======================================================
    // Public
    //=======================================================

    /**
     * 文件是否存在
     */
    public static boolean isExist(File file) {
        return file != null && file.exists();
    }

    /**
     * 创建SDCard根目录
     * /qijia
     */
    @Nullable
    public static File getRootDir() {
        if (isSDCardAvailable()) {
            File rootDir = new File(Environment.getExternalStorageDirectory(),
                    IBaseConstants.DIR_NAME_ROOT);
            if (mkDirs(rootDir)) {
                return rootDir;
            }
        }
        return null;
    }

    /**
     * 装修ZXERP的目录
     * /qijia/zxerp
     */
    @Nullable
    public static File getPlatformDir() {

        if (getRootDir() != null) {
            File zxerpDir = new File(getRootDir().getAbsolutePath() + File.separator + IBaseConstants.DIR_NAME_PLATFORM);
            if (mkDirs(zxerpDir)) {
                return zxerpDir;
            }
        }
        return null;
    }

    /**
     * 装修ZXERP的user目录
     * /qijia/zxerp/user
     */
    @Nullable
    public static File getAppDir() {

        if (getPlatformDir() != null) {
            File zxerpDir = new File(getPlatformDir().getAbsolutePath() + File.separator + IBaseConstants.DIR_NAME_APP);
            if (mkDirs(zxerpDir)) {
                return zxerpDir;
            }
        }
        return null;
    }

    /**
     * 装修ZXERP的user目录
     * /qijia/zxerp/user/crash
     */
    @Nullable
    public static File getCrashDir() {
        if (getAppDir() != null) {
            File zxerpDir = new File(getAppDir().getAbsolutePath() + File.separator + IBaseConstants.DIR_NAME_CRASH);
            if (mkDirs(zxerpDir)) {
                return zxerpDir;
            }
        }
        return null;
    }


    /**
     * 获取崩溃的文件
     */
    @Nullable
    public static File getCrashFile() {
        if (getCrashDir() != null) {
            return new File(getCrashDir().getAbsolutePath() +
                    File.separator +
                    IBaseConstants.FILE_NAME_CRASH +
                    DateUtils.getLog(System.currentTimeMillis()) +
                    IBaseConstants.FILE_EXTENSION_TXT);
        }
        return null;
    }

    /**
     * 判断SDCard是否可用
     */
    public static boolean isSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * @param path    文件路径
     * @param content 写入的文本内容
     */
    public static void writeDataToSdCard(String path, String content) {
        // 要写入的文本文件
        File file = new File(path);

        File dirFile = new File(file.getParent());
        if (!new File(file.getParent()).exists()) {
            //创建目录
            dirFile.mkdirs();
        }

        RandomAccessFile raf = null;
        // 如果文件不存在，则创建该文件
        if (!file.exists()) {
            try {
                file.createNewFile();
                raf = new RandomAccessFile(file, "rw");
                raf.write(content.getBytes(IBaseConstants.ENCODE_TYPE_UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (raf != null) {
                    try {
                        // 关闭输出流，施放资源
                        raf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //=============================
    // 私有的
    //=============================

    /**
     * 创建子目录
     *
     * @param file File
     * @return true: 存在  false : 不存在
     */
    private static boolean mkDirs(@NonNull File file) {
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

}

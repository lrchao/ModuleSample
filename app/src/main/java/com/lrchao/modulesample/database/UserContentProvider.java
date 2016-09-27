package com.lrchao.modulesample.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.jia.jiacore.database.ContentProviderProxy;
import com.lrchao.modulesample.BuildConfig;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/27 下午2:09
 */

public class UserContentProvider extends ContentProvider {

    /**
     * 发布的数据库版本
     */
    public static final int RELEASE_VERSION_1_0_0 = 1; // app version 1.0
    public static final int RELEASE_VERSION_1_0_2 = 2; // app version 1.0.2
    public static final int RELEASE_VERSION_1_1_5 = 3; // app version 1.1.5

    private static final String DATABASE_NAME = "zxpt_user.db";

    /**
     * 当前版本号
     */
    private static final int CUR_DATABASE_VERSION = RELEASE_VERSION_1_1_5;

    /**
     * 代理
     */
    private ContentProviderProxy mContentProviderProxy;


    @Override
    public boolean onCreate() {
        ContentProviderProxy.AUTHORITIES = BuildConfig.APPLICATION_ID;
        mContentProviderProxy = new ContentProviderProxy();
        mContentProviderProxy.setContext(getContext());
        mContentProviderProxy.setDatabaseName(DATABASE_NAME);
        mContentProviderProxy.setCurrentDatabaseVersion(CUR_DATABASE_VERSION);
        return mContentProviderProxy.onCreate();
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return mContentProviderProxy.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return mContentProviderProxy.getType(uri);
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return mContentProviderProxy.insert(uri, values);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mContentProviderProxy.delete(uri, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return mContentProviderProxy.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        return mContentProviderProxy.bulkInsert(uri, values);
    }


}

package com.lrchao.modulesample.database.table;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.jia.jiacore.database.DBTableBase;
import com.jia.jiacore.util.LogUtils;
import com.lrchao.modulesample.ModuleApplication;
import com.lrchao.modulesample.database.TableFactory;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/27 下午2:30
 */

public class HttpCacheTable extends DBTableBase{

    /**
     * 表名
     */
    public static final String TABLE_NAME = "http_cache";

    /**
     * url
     * <P>Type: String</P>
     */
    public static final String COLUMN_URL = "_url";
    /**
     * response
     * <P>Type: String</P>
     */
    public static final String COLUMN_RESPONSE = "_response";
    /**
     * date
     * <P>Type: Long</P>
     */
    public static final String COLUMN_DATE = "_date";

    {
        mTag = "HttpCacheTable";
    }

    /**
     * 插入
     *
     * @param url      请求的URL
     * @param response 请求的相应
     */
    public static Uri insert(String url, String response) {
        return insert(url, response, ModuleApplication.getApplication().getContentResolver());
    }

    private static Uri insert(String url, String response, ContentResolver contentResolver) {
        ContentValues values = getContentValues(url, response);
        return contentResolver.insert(TableFactory.getTableHttpCacheUri(), values);
    }

    private static ContentValues getContentValues(String url, String response) {
        ContentValues values = new ContentValues(3);
        values.put(COLUMN_URL, url);
        values.put(COLUMN_RESPONSE, response);
        values.put(COLUMN_DATE, System.currentTimeMillis());
        return values;
    }

    /**
     * 删除所有数据
     */
    public static int delete() {
        return delete(ModuleApplication.getApplication().getContentResolver());
    }

    private static int delete(ContentResolver contentResolver) {
        return contentResolver.delete(TableFactory.getTableHttpCacheUri(), null, null);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public int getTableCode() {
        return TableFactory.TABLE_HTTP_CACHE_CODE;
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        StringBuilder createTable = new StringBuilder();
        createTable.append("CREATE TABLE IF NOT EXISTS ");
        createTable.append(mTableName);
        createTable.append(" (");
        createTable.append(COLUMN_ID);
        createTable.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        createTable.append(COLUMN_URL);
        createTable.append(" TEXT NOT NULL,");
        createTable.append(COLUMN_RESPONSE);
        createTable.append(" TEXT NOT NULL,");
        createTable.append(COLUMN_DATE);
        createTable.append(" INTEGER NOT NULL,");
        createTable.append(" UNIQUE (");
        createTable.append(COLUMN_URL);
        createTable.append(" ) ON CONFLICT REPLACE");
        createTable.append(");");
        try {
            db.execSQL(createTable.toString());
        } catch (SQLException ex) {
            LogUtils.e(mTag, "couldn't create table " + mTableName);
        }
    }

}

package com.jia.jiacore.database;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jia.jiacore.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: ContentProvider的代理类
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/27 下午2:00
 */

public class ContentProviderProxy {

    private static final String TAG = "ContentProviderProxy";

    private static final UriMatcher URI_MATCHER;

    public static String AUTHORITIES;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

        for (DBTableBase table : IBaseTableFactory.getTables()) {
            URI_MATCHER.addURI(/*DBTableBase.AUTHORITIES*/AUTHORITIES, table.getTableName(), table.getTableCode());
        }
    }

    /**
     * SQLiteOpenHelper
     */
    private SQLiteOpenHelper mOpenHelper = null;

    private String mDatabaseName;

    private int mCurrentDatabaseVersion;

    private Context mContext;

    /**
     * 从URI中获取表名
     *
     * @param uri 目标Uri
     * @return 操作目标的表名
     */
    private static String getTableFromUri(final Uri uri) {
        return uri.getPathSegments().get(0);
    }

    public void setDatabaseName(String databaseName) {
        mDatabaseName = databaseName;
    }

    public void setCurrentDatabaseVersion(int currentDatabaseVersion) {
        mCurrentDatabaseVersion = currentDatabaseVersion;
    }

    /**
     * @param context 不要用Application的context, 要是用ContentProvider的getContext();
     */
    public void setContext(Context context) {
        mContext = context;
    }

    /**
     * ContentProvider.onCreate()
     */
    public boolean onCreate() {
        mOpenHelper = new DatabaseHelper(mContext);
        return true;
    }

    /**
     * ContentProvider.query()
     */
    public Cursor query(@NonNull Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        int match = URI_MATCHER.match(uri);
        if (match == -1) {
            LogUtils.d(TAG, "querying unknown URI: " + uri);
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        SqlSelection fullSelection = getWhereClause(selection, selectionArgs);
        logVerboseQueryInfo(projection, selection, selectionArgs, sortOrder, db);
        String table = getTableFromUri(uri);
        Cursor ret = db.query(table, projection, fullSelection.getSelection(),
                fullSelection.getParameters(), null, null, sortOrder);
        if (ret == null) {
            LogUtils.d(TAG, "query failed in market database");
        }
        return ret;
    }

    /**
     * ContentProvider.getType()
     */
    public String getType(@NonNull Uri uri) {
        int match = URI_MATCHER.match(uri);
        DBTableBase table = IBaseTableFactory.get(match);
        if (table != null) {
            return table.getTableType();
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    /**
     * ContentProvider.insert()
     */
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String table = getTableFromUri(uri);
        long rowID = db.insert(table, null, values);
        if (rowID == -1) {
            LogUtils.d(TAG, "couldn't insert into " + table + " database");
            return null;
        }
        return ContentUris.withAppendedId(uri, rowID);
    }

    /**
     * ContentProvider.delete()
     */
    public int delete(@NonNull Uri uri, String where, String[] whereArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String table = getTableFromUri(uri);
        SqlSelection selection = getWhereClause(where, whereArgs);
        int count = db.delete(table, selection.getSelection(), selection.getParameters());
        if (count == 0) {
            LogUtils.d(TAG, "couldn't delete URI " + uri);
            return count;
        }
        return count;
    }

    /**
     * ContentProvider.update()
     */
    public int update(@NonNull Uri uri, ContentValues values, String where, String[] whereArgs) {
        String table = getTableFromUri(uri);
        SqlSelection selection = new SqlSelection();
        selection.appendClause(where, whereArgs);
        int match = URI_MATCHER.match(uri);
        if (match == -1) {
            selection.appendClause(DBTableBase.COLUMN_ID + " = ?", getIdFromUri(uri));
        }
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count = db.update(table, values, selection.getSelection(), selection.getParameters());
        if (count == 0) {
            LogUtils.d(TAG, "couldn't updateF URI " + uri);
            return count;
        }
        return count;
    }


    public int bulkInsert(Uri uri, ContentValues[] values) {
        int numValues = 0;
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        //开始事务
        db.beginTransaction();
        try {
            numValues = values.length;
            for (int i = 0; i < numValues; i++) {
                insert(uri, values[i]);
            }
            //别忘了这句 Commit
            db.setTransactionSuccessful();
        } finally {
            //结束事务
            db.endTransaction();
        }
        return numValues;
    }

    private String getIdFromUri(final Uri uri) {
        return uri.getPathSegments().get(1);
    }

    /**
     * Notify of a change through both URIs
     * 通知注册的观察者，指定URI数据更新
     */

    @SuppressWarnings("ConstantConditions")
    private void notifyContentChanged(Uri uri) {
        mContext.getContentResolver().notifyChange(uri, null);
    }

    /**
     * 获取SQL条件的工具方法
     *
     * @param where     条件
     * @param whereArgs 参数
     * @return 合成的SqlSelection对象
     */
    private SqlSelection getWhereClause(String where, String[] whereArgs) {
        SqlSelection selection = new SqlSelection();
        selection.appendClause(where, whereArgs);
        return selection;
    }

    /**
     * 打印 【查询SQL】 详细信息
     */
    private void logVerboseQueryInfo(String[] projection, String selection, String[] selectionArgs,
                                     String sort, SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("starting query, database is ");
        if (db != null) {
            sb.append("not ");
        }
        sb.append("null; ");
        if (projection == null) {
            sb.append("projection is null; ");
        } else if (projection.length == 0) {
            sb.append("projection is empty; ");
        } else {
            for (int i = 0; i < projection.length; ++i) {
                sb.append("projection[");
                sb.append(i);
                sb.append("] is ");
                sb.append(projection[i]);
                sb.append("; ");
            }
        }
        sb.append("selection is ");
        sb.append(selection);
        sb.append("; ");
        if (selectionArgs == null) {
            sb.append("selectionArgs is null; ");
        } else if (selectionArgs.length == 0) {
            sb.append("selectionArgs is empty; ");
        } else {
            for (int i = 0; i < selectionArgs.length; ++i) {
                sb.append("selectionArgs[");
                sb.append(i);
                sb.append("] is ");
                sb.append(selectionArgs[i]);
                sb.append("; ");
            }
        }
        sb.append("sort is ");
        sb.append(sort);
        sb.append(".");
        LogUtils.d(TAG, sb.toString());
    }

    //=============================================
    // SqlSelection
    //=============================================

    /**
     * Description: 这个类封装了一个SQL WHERE子句和它的参数
     *
     * @author lrc19860926@gmail.com
     * @date 15/11/11 下午11:17
     */
    private class SqlSelection {
        private StringBuilder mWhereClause = new StringBuilder();
        private List<String> mParameters = new ArrayList<>();

        @SuppressWarnings("unchecked")
        public <T> void appendClause(String newClause, final T... parameters) {
            if (TextUtils.isEmpty(newClause)) {
                return;
            }
            if (mWhereClause.length() != 0) {
                mWhereClause.append(" AND ");
            }
            mWhereClause.append("(");
            mWhereClause.append(newClause);
            mWhereClause.append(")");
            if (parameters != null && parameters.length > 0) {
                for (Object parameter : parameters) {
                    mParameters.add(parameter.toString());
                }
            }
        }

        public String getSelection() {
            return mWhereClause.toString();
        }

        public String[] getParameters() {
            String[] array = new String[mParameters.size()];
            return mParameters.toArray(array);
        }
    }

    //=============================================
    // SQLiteOpenHelper
    //=============================================

    /**
     * Description: 从SQLiteOpenHelper 派生子类处理数据的创建、升级
     *
     * @author lrc19860926@gmail.com
     * @date 15/11/11 下午11:12
     */
    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(final Context context) {
            super(context, mDatabaseName, null, mCurrentDatabaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            LogUtils.d(TAG, "create the new database...");
            createTables(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            LogUtils.d(TAG, "update the database...oldVersion=" + oldVersion + ",newVersion=" + newVersion);
            int upgradeVersion = oldVersion;
            for (; upgradeVersion < mCurrentDatabaseVersion; upgradeVersion++) {
                doUpgrade(db, upgradeVersion);
            }

        }

        /**
         * 创建所有表
         *
         * @param db 数据库对象
         */
        private void createTables(SQLiteDatabase db) {
            for (DBTableBase table : IBaseTableFactory.getTables()) {
                table.createTable(db);
            }
        }

        /**
         * 处理数据库创建，表的创建,根据版本号判断处理表结构的升级
         *
         * @param db             数据库对象
         * @param upgradeVersion 依次升级的版本
         */
        private void doUpgrade(SQLiteDatabase db, int upgradeVersion) {
            for (DBTableBase table : IBaseTableFactory.getTables()) {
                table.upgradeTable(db, upgradeVersion);
            }
        }
    }
}

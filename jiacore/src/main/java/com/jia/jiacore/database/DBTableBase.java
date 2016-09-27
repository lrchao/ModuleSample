package com.jia.jiacore.database;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.jia.jiacore.util.LogUtils;

/**
 * Description: 数据库表基类
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/27 下午1:59
 */

public abstract class DBTableBase {

    /**
     * The unique ID for a row.
     * <P>Type: INTEGER (long)</P>
     */
    public static final String COLUMN_ID = "_id";
    /**
     * 删除表前缀
     */
    private static final String DROP_TABLE_PREFIX = "DROP TABLE IF EXISTS ";

    /**
     * 标记
     */
    protected String mTag = "";
    /**
     * 表名
     */
    protected String mTableName;


    public DBTableBase() {
        mTableName = getTableName();
    }

    /**
     * 子类继承设置表名
     *
     * @return 表名
     */
    public abstract String getTableName();

    /**
     * 获取表的对应类型
     *
     * @return 表的类型
     */
    public String getTableType() {
        return IBaseTableFactory.LIST_TYPE + getTableName();
    }

    /**
     * 要求子类指定表Code值
     *
     * @return 表的Code
     */
    public abstract int getTableCode();

    /**
     * 要求子类实现表格创建代码
     *
     * @param db 数据库
     */
    public abstract void createTable(SQLiteDatabase db);

    /**
     * 要求子类指定表访问地址
     *
     * @return 表对应的uri
     */
    public Uri getTableUri() {
        return Uri.parse("content://" + ContentProviderProxy.AUTHORITIES + "/" + getTableName());
    }

    /**
     * 需要升级数据库的表，子类选择性实现
     *
     * @param db         数据库对象
     * @param oldVersion 被覆盖安装的版本号
     */
    public void upgradeTable(SQLiteDatabase db, int oldVersion) {
        LogUtils.i(mTag, "on upgrade table " + mTableName + " db:" + db + " oldVersion=" + oldVersion);
    }

    @Override
    public String toString() {
        return mTableName;
    }

    /**
     * 删除表，当数据库升级没有升级成功或异常后 删掉所有表并重新创建
     *
     * @param db 数据库对象
     */
    public void dropTable(SQLiteDatabase db) {
        db.execSQL(DROP_TABLE_PREFIX + mTableName);
    }
}

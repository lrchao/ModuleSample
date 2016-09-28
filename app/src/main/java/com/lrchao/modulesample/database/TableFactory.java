package com.lrchao.modulesample.database;

import android.net.Uri;

import com.jia.jiacore.database.DBTableBase;
import com.jia.jiacore.database.IBaseTableFactory;
import com.lrchao.modulesample.database.table.HttpCacheTable;

/**
 * Description: 表工厂,负责初始化表
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/27 下午2:17
 */

public final class TableFactory extends IBaseTableFactory {

    /**
     * 表的URI对应的code和uri
     */
    public static final int TABLE_HTTP_CACHE_CODE = 1; // 请求缓存表 Code

    private static Uri sTableHttpCacheUri;

    static {
        initTables();
    }

    private TableFactory() {

    }

    /**
     * 初始化表
     */
    private static void initTables() {
        // 添加推送订单表
        DBTableBase httpCacheTable = new HttpCacheTable();
        sTableHttpCacheUri = httpCacheTable.getTableUri();

        mTables.add(httpCacheTable);
    }

    /**
     * 获取接口缓存表的URI
     */
    public static Uri getTableHttpCacheUri() {
        return sTableHttpCacheUri;
    }
}

package com.jia.jiacore.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: TableFactory的基类
 * 子类需要在static域内初始化表
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/27 下午2:01
 */

public abstract class IBaseTableFactory {

    /**
     * 访问所有表的数据是列表类型
     */
    public static final String LIST_TYPE = "vnd.android.cursor.dir/";

    protected static List<DBTableBase> mTables = new ArrayList<>();

    /**
     * 根据表的Code 获取对应的表
     *
     * @param tableCode 表编号
     * @return table对象
     */
    public static DBTableBase get(int tableCode) {
        for (DBTableBase table : mTables) {
            if (tableCode == table.getTableCode()) {
                return table;
            }
        }
        return null;
    }

    /**
     * 获取一个值，表示所有表的集合
     */
    static List<DBTableBase> getTables() {
        return mTables;
    }
}

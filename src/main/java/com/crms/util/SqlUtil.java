package com.crms.util;

public class SqlUtil {
    /**
     * select * from sys_role where 列=? and 列 like ? limit ?,?;
     * select count(*) from sys_role where 列=? and 列 like ?;
     * 分页语句转为聚合语句
     * @param sql 分页语句
     * @return 聚合语句
     */

    public static String sqlCount(String sql){
        sql=sql.toLowerCase();
        int fromIndex= sql.indexOf(" from ");
        int limitIndex= sql.lastIndexOf("limit");
        String reSql="select count(*) "+sql.substring(fromIndex,limitIndex);
        return reSql;
    }
}

package utils;

import java.sql.SQLException;

/**
 * @Author miujoke
 * @Description TODO
 * @Date 2021/1/29 13:58
 */
public class Demo {
    public static void main(String[] args) throws SQLException {
        /**
         * sql 字符串既是原生的sql语句
         */
        // 需要执行的sql语句----增删改均用此方法
        String sql = "insert into user(username,password,email) values('miujoke','miujoke','admin@qq.com')";
        DBUtil.update(sql);
        // 需要执行的sql语句----查用此方法
//        DBUtil.query(sql);
    }
}

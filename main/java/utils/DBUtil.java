package utils;

import java.sql.*;

/**
 * @Author miujoke
 * @Description TODO
 * @Date 2021/1/29 11:44
 */
public class DBUtil {
    /*
    <!--mysql 依赖包-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.18</version>
    </dependency>
     */
    private ResultSet rs;
    private Statement stmt;
    private Connection conn;
    // 连接数据库的账户一般都是root
    final static String username = "root";
    // 连接数据库的密码，此处根据自己的本地数据库填写
    final static String password = "admin";
    // 此处 192.168.6.128 是我的数据库地址，如果你们是本地数据库直接改成127.0.0.1或者localhost；3306端口号；miujoke是数据库名称；后面一串是解决时区问题
    final static String url = "jdbc:mysql://192.168.6.128:3306/miujoke?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai";

    static {
        try {
            // 注册驱动 此处用的是 MySQL8.0版本
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 连接到数据库
    public static Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException throwables) {
            System.out.println("数据库连接失败，请检查是否连接参数有问题出问题");
        }
        return conn;
    }
   // 查询
    public static void query(String sql) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        //获取数据库连接对象
        connection = DBUtil.getConnection();
        //通过connection获取操作类
        statement = connection.createStatement();
        //返回结果集
        resultSet = statement.executeQuery(sql);
        //遍历resultSet打印数据
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username") + "---" + resultSet.getString("password"));
        }
        // 关闭连接资源
        DBUtil.closeJDBC(resultSet, statement, connection);
    }
    // 修改
    public static void update(String sql) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        int result = 0;
        //获取数据库连接对象
        connection = DBUtil.getConnection();
        //通过connection获取操作类
        statement = connection.createStatement();
        //返回结果集
        result=statement.executeUpdate(sql);
        System.out.println(result >0?"成功":"失败");
        // 关闭连接资源
        DBUtil.closeJDBC(null, statement, connection);
    }

    // 关闭资源
    public static void closeJDBC(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

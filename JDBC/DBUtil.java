package JDBC;

import java.sql.*;

/**
 *
 */
public class DBUtil {

    //
    public static final String URL = "jdbc:mysql://localhost:3306/company";
    public static final String USER ="root";
    public static final String PASS = "1800438578";

    //驱动
    static{

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库驱动成功！");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.err.println("数据库驱动失败");
            e.printStackTrace();
        }
    }

    //连接
    public static Connection getConn(){

        Connection conn=null;


        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("数据库连接成功！");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("数据库连接失败！");
            e.printStackTrace();
        }
        return conn;
    }

    //关闭
    public static void close(ResultSet rs, Statement stat, Connection conn){
        try {
            if(rs!=null)
                rs.close();
            rs=null;
            if(stat!=null)
                stat.close();
            stat=null;
            if(conn!=null)
                conn.close();
            conn=null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}

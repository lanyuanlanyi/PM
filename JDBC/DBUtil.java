package JDBC;

import java.sql.*;

/**
 *
 */
public class DBUtil {

    //
    public static final String URL = "jdbc:mysql://localhost:3306/company";
    public static final String USER ="root";
    public static final String PASS = "admin";

    //����
    static{

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("���ݿ������ɹ���");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.err.println("���ݿ�����ʧ��");
            e.printStackTrace();
        }
    }

    //����
    public static Connection getConn(){

        Connection conn=null;


        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("���ݿ����ӳɹ���");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("���ݿ�����ʧ�ܣ�");
            e.printStackTrace();
        }
        return conn;
    }

    //�ر�
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

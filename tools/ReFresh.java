package tools;

import JDBC.DBUtil;
import JDBC.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by 85168 on 2017/3/25.
 */
public class ReFresh {

    //创建新表复制内容      表赋值前需要先清空SELECT * FROM `p`;  DELETE FROM p;
    //交换表             a,b;
    // int temp;        create table temp like a;
    // temp=a;          insert into temp select * from a;
    // a=b;             insert into a select * from b;
    // b=tmp            insert into b select * from temp;

    //交换表
    public void tempSql(){

        Connection conn =null;
        conn = DBUtil.getConn();
        Statement stat =null;
        ResultSet rs = null;
        String[] sqls = new String[7];

        sqls[0] = "SELECT * FROM `person`";
        sqls[1] =      "insert into temp select * from person" ;
        sqls[2] =      "DELETE FROM person";
        sqls[3] =      "insert into person select * from findresult" ;
        sqls[4] =      "DELETE FROM findresult" ;
        sqls[5] =      "insert into findresult select * from temp" ;
        sqls[6] =      "DELETE FROM temp" ;

        try {
            stat = conn.createStatement();
            for (int i = 0; i <7 ; i++) {

                stat.execute(sqls[i]);
            }

            System.out.println("表交换成功！");
        } catch (SQLException e) {

            System.err.println("交换失败");
            //e.printStackTrace();
        } finally{
            DBUtil.close(rs, stat, conn);
            System.out.println("数据库已安全关闭<(*^V^*)>~");
        }

    }

    //清空结果表findresult
    public void cleanResult(){

        Connection conn =null;
        conn = DBUtil.getConn();
        Statement stat =null;
        ResultSet rs = null;
        String sql =  "DELETE FROM findresult" ;

        try {
            stat = conn.createStatement();
            stat.execute(sql);

            System.out.println("结果清空成功！");
        } catch (SQLException e) {

            System.err.println("结果清空失败，sql语句为：");
            //e.printStackTrace();
        } finally{
            DBUtil.close(rs, stat, conn);
            System.out.println("数据库已安全关闭<(*^V^*)>~");
        }

    }


    //判断person表是否为person---是否big
    //清空结果表findresult
    public boolean personBig(){

        boolean flag=false;

        Connection conn =null;
        conn = DBUtil.getConn();
        Statement stat =null;
        ResultSet rsP = null;
        ResultSet rsR = null;
        String sqlPerson =  "select *FROM person" ;
        String sqlResult =  "select *FROM findresult" ;

        try {
            stat = conn.createStatement();

            rsP=stat.executeQuery(sqlPerson);
            rsP.last();
            int p=rsP.getRow();

            rsR=stat.executeQuery(sqlResult);
            rsR.last();
            int r=rsR.getRow();
            if (p>r)    flag=true;

            //System.out.println(p+"<>"+r);
            //System.out.println("结果清空成功！");
        } catch (SQLException e) {
            System.err.println("比较大小失败");
            e.printStackTrace();
        } finally{
            //4.关闭数据库
            DBUtil.close(rsP, stat, conn);
            System.out.println("数据库已安全关闭<(*^V^*)>~");
        }

        return flag;
    }


    //获得所有
    public ArrayList<Person> getAll(){

        ArrayList<Person> al = new ArrayList<Person>();

        Connection conn =null;
        conn = DBUtil.getConn();
        Statement stat =null;      	//
        ResultSet rs = null;		//
        String sql = null;			//

        sql = "select * from person";


        try {
            stat = conn.createStatement();

            rs = stat.executeQuery(sql);
            while(rs.next()){

                int id = rs.getInt(1);
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String dep = rs.getString("dep");
                int sal = rs.getInt("sal");

                Person p = new Person(id,name,sex,dep,sal);
                al.add(p);
            }

            System.out.println("查询成功！");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //System.err.println("增加记录失败！sql语句为："+sql);    //出错一般都是sql语句出错，这里输出检查
            System.err.println("查询失败！sql语句为："+sql);
            e.printStackTrace();
        } finally{
            //4.关闭数据库
            DBUtil.close(rs, stat, conn);
            System.out.println("数据库已安全关闭<(*^V^*)>~");
        }

        return al;
    }


}

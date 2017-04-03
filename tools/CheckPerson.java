package tools;

import JDBC.DBUtil;
import JDBC.Person;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by 85168 on 2017/3/25.
 */
public class CheckPerson {

    //检查主键
    public boolean checkId(Person u) {

        boolean flag=false;
        Connection conn = null;
        conn = DBUtil.getConn();
        ResultSet rs = null;

        PreparedStatement pstmt=null;
        String sql=null;
        sql="select id from person where id=?";

        try {
            pstmt=conn.prepareStatement(sql);

            pstmt.setInt(1,u.getId());
            rs = pstmt.executeQuery();

            if (flag=rs.next()){
                System.out.println("ID重复");
            }else flag=false;

        } catch (SQLException e) {
            //e.printStackTrace();
            //flag=false;
            //System.err.println("检查主键失败");
        }
        return flag;
    }

    //检查管理员
    public boolean checkAdmin(Person u) {

        boolean flag=false;
        Connection conn = null;
        conn = DBUtil.getConn();
        ResultSet rs = null;

        PreparedStatement pstmt=null;
        String sql=null;
        sql="select id from person where id=? and pin=?";

        try {
            pstmt=conn.prepareStatement(sql);

            pstmt.setInt(1,u.getId());
            //pstmt.setInt(2,u.getPin());
            rs = pstmt.executeQuery();

            flag=rs.next();

            System.out.println("检查主键成功");
        } catch (SQLException e) {
            e.printStackTrace();
            flag=false;
            System.err.println("检查主键失败");
        }

        return flag;
    }


    //将查询结果  批量添加到查找结果表
    public void insert(ArrayList<Person> al) {

        Connection conn = null;
        conn = DBUtil.getConn();

        PreparedStatement pstmt=null;
        String sql=null;
        sql="insert into findresult(id,name,sex,dep,sal) values(?,?,?,?,?)";


        try {
            pstmt=conn.prepareStatement(sql);

            for (Person u:al) {

                pstmt.setInt(1,u.getId());
                pstmt.setString(2,u.getName());
                pstmt.setString(3,u.getSex());
                pstmt.setString(4,u.getDep());
                pstmt.setInt(5,u.getSal());

                pstmt.executeUpdate();
                System.out.println("增加记录成功");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("增加记录失败");
        }
    }


//查询的实现========
    //================
    //====================
    //=======================
    //===========================
    //===============================================================

    public ArrayList<Person> getResult( JTextField tf1, JTextField tf2,
            JComboBox<String> jcb3,JComboBox<String> jcb4,
            JTextField tf5,JComboBox<String> jcb6){

        ArrayList<Person> al = new ArrayList<Person>();

        Connection conn = null;
        conn = DBUtil.getConn();
        ResultSet rs = null;
        Statement stmt=null;

        String id = tf1.getText();                      boolean f1="".equals(id);
        String name =tf2.getText();                     boolean f2="".equals(name);
        String sex = jcb3.getSelectedItem().toString(); boolean f3="".equals(sex);
        String dep = jcb4.getSelectedItem().toString(); boolean f4="".equals(dep);
        String sal = tf5.getText();                     boolean f5="".equals(sal);
        String sel=jcb6.getSelectedItem().toString();

        //System.out.println(f1+"<2>"+f2+"<3>"+f3+"<4>"+f4+"<5>"+f5);
        String sql="select *from person where 1=1 ";
        if ("or".equals(sel))
            sql="select *from person where 1=0 ";
        if(f1&&f2&&f3&&f4&&f5)  sql="select *from person";
        if (!f1)    sql+=(sel+" id like "+"'%"+id+"%'");
        if (!f2)    sql+=(sel+" name like "+"'%"+name+"%'");
        if (!f3)    sql+=(sel+" sex="+"'"+sex+"'");
        if (!f4)    sql+=(sel+" dep="+"'"+dep+"'");
        if (!f5)    sql+=(sel+" sal like "+"'%"+sal+"%'");

        //System.out.println(sql);

        try {
            stmt=conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){

                ///都是rr
                int idr = rs.getInt(1);
                String namer = rs.getString("name");
                String sexr = rs.getString("sex");
                String depr = rs.getString("dep");
                int salr = rs.getInt("sal");

                Person p = new Person(idr,namer,sexr,depr,salr);
                System.out.println(p);
                al.add(p);
            }
            if (al.size()==0)   System.out.println("查无结果");
            else    System.out.println("查询成功");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("查询异常！sql语句为："+sql);
            //e.printStackTrace();
        } finally{
            DBUtil.close(rs, stmt, conn);
            System.out.println("数据库已关闭-");
        }

        return al;
    }





}

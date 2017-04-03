package tools;

import JDBC.DBUtil;
import JDBC.Person;
import windows.PlusView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 85168 on 2017/3/25.
 */
public class EditPerson {


    //增加一条
    public boolean insert(Person u) {

        boolean flag=false;
        Connection conn = null;
        conn = DBUtil.getConn();

        CheckPerson ck=new CheckPerson();
        if (ck.checkId(u)) {

        }

        PreparedStatement pstmt=null;
        String sql=null;
        sql="insert into person(id,name,sex,dep,sal) values(?,?,?,?,?)";

        try {
            pstmt=conn.prepareStatement(sql);

            pstmt.setInt(1,u.getId());
            pstmt.setString(2,u.getName());
            pstmt.setString(3,u.getSex());
            pstmt.setString(4,u.getDep());
            pstmt.setInt(5,u.getSal());

            pstmt.executeUpdate();
            flag=true;
            //JOptionPane.showMessageDialog(null,"已增加成功");
            //System.out.println("增加记录成功");
        } catch (SQLException e) {
            //e.printStackTrace();

            flag=false;
        }
        return flag;
    }
 

    //删除一条

    public void  delete(Person u) {

        Connection conn = null;
        conn = DBUtil.getConn();

        PreparedStatement pstmt=null;
        String sql=null;
        sql="delete from person where id=?";

        try {
            pstmt=conn.prepareStatement(sql);

            pstmt.setInt(1,u.getId());
            /*pstmt.setString(2,u.getName());
            pstmt.setString(3,u.getSex());
            pstmt.setString(4,u.getDep());
            pstmt.setInt(5,u.getSal());*/

            pstmt.executeUpdate();
            System.out.println("删除记录成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("删除记录失败");
        }
    }

    //增加多条

    public void  addAll(ArrayList<Person> al) {

        for (int i = 0; i <al.size() ; i++) {

            insert(al.get(i));
        }
    }

    //删除多条

    public void  deleteAll(ArrayList<Person> al) {

        for (int i = 0; i <al.size() ; i++) {

            delete(al.get(i));
        }
    }


    //通过ID返回一个PERSON
    public Person getPersonFromId(int id){

        Connection conn = null;
        conn = DBUtil.getConn();
        ResultSet rs = null;
        Person p=null;

        PreparedStatement pstmt=null;
        String sql=null;
        sql="select *from person where id=?";

        try {
            pstmt=conn.prepareStatement(sql);

            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            rs.next();

            int idb = rs.getInt(1);
            String name = rs.getString("name");
            String sex = rs.getString("sex");
            String dep = rs.getString("dep");
            int sal = rs.getInt("sal");

            p = new Person(idb,name,sex,dep,sal);

            System.out.println("生成备用person成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("生成备用person失败");
        }

        return p;


    }

    //从PV获得一个person
    public Person getPersonFromPV(PlusView pv){

        String id = pv.tf1.getText();
        String name = pv.tf2.getText();
        String sex = pv.jcb3.getSelectedItem().toString();
        String dep = pv.jcb4.getSelectedItem().toString();
        String sal = pv.tf5.getText();

        if ("".equals(id));
        if ("".equals(name))    name="default";
        if ("".equals(sex))     sex="default";
        if ("".equals(dep))     dep="default";
        if ("".equals(sal))     sal="0";

        Person person = new Person(Integer.parseInt(id),
                name, sex, dep, Integer.parseInt(sal));

        return person;
    }

    //从PV获得一个person
    public Person getPersonFromPV(JTextField tf1,
               JTextField tf2,JComboBox jcb3,JComboBox  jcb4,JTextField tf5){

        String id = tf1.getText();
        String name = tf2.getText();
        String sex = jcb3.getSelectedItem().toString();
        String dep = jcb4.getSelectedItem().toString();
        String sal = tf5.getText();

        if ("".equals(id));
        if ("".equals(name))    name="default";
        if ("".equals(sex))     sex="default";
        if ("".equals(dep))     dep="default";
        if ("".equals(sal))     sal="0";

        Person person = new Person(Integer.parseInt(id),
                name, sex, dep, Integer.parseInt(sal));
        return person;
    }

    public boolean isSame(Person p1,Person p2){

        boolean flag=false;

        boolean f1=p1.getId()==p2.getId();
        boolean f2=p1.getName().equals(p2.getName());
        boolean f3=p1.getSex().equals(p2.getSex());
        boolean f4=p1.getDep().equals(p2.getDep());
        boolean f5=p1.getSal()==p2.getSal();

        flag=(f1 && f2 && f3 && f4 && f5);

        return flag;
    }


}

package tools;

import JDBC.Admin;
import JDBC.DBUtil;
import windows.MainView;
//import JDBC.Person;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 85168 on 2017/3/28.
 */
public class AdminEdit {


    public void login(JFrame frame, JLabel label,
            JTextField tf1,JPasswordField tf2,
            JCheckBox jcb1,JCheckBox jcb2){

        boolean f1=jcb1.isSelected();
        boolean f2=jcb2.isSelected();
        if ((!f1)&&(!f2)){
            //����¼
            if (checkPAss(tf1,tf2)){
                frame.dispose();
                MainView mv=new MainView();
                mv.frame.setTitle(tf1.getText()+"����ã�����й���");

            }
        }else if (f1&&(!f2)){

            frame.dispose();
            MainView mv=new MainView();
            mv.button1.setVisible(false);
            mv.button2.setVisible(false);
            mv.button3.setVisible(false);
            mv.item1.setVisible(false);
            mv.item2.setVisible(false);
            mv.item3.setVisible(false);
            mv.item5.setVisible(false);
            mv.item6.setVisible(false);
            mv.frame.setTitle("��ã��οͣ���ӭ��ѯ");
            mv.plus.setVisible(false);


        }else if((!f1)&&f2){
            //�л���ע��ҳ��
            SignIn0(tf1,tf2,jcb2,frame,label);

        }else if (f1&&f2){
            //��ʾ�Ƿ�����
            JOptionPane.showMessageDialog(null,"����ͬʱ��ѡ�����飡");
            return;
        }
    }

    //ע���û�-0-���� 1-������

    public boolean SignIn0(JTextField tf1,JPasswordField tf2,JCheckBox jcb2,JFrame frame,JLabel label){

        String id = tf1.getText();
        String pass= String.valueOf(tf2.getPassword());
        AdminEdit ae=new AdminEdit();

        boolean flag=false;
        Connection conn = null;
        conn = DBUtil.getConn();

        CheckPerson ck=new CheckPerson();
        if (ae.checkId(id)) {

            System.out.println("ID�ظ�");
        }

        PreparedStatement pstmt=null;
        String sql=null;
        sql="insert into admin(id,pass,allowed) values(?,?,"+"'0'"+")";

        try {
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,id);
            pstmt.setString(2,pass);

            pstmt.executeUpdate();
            flag=true;
            JOptionPane.showMessageDialog(null,"ע�����Ա�ɹ������μ��û���������\n��ֱ�ӵ�¼");
            jcb2.setSelected(false);
            frame.setTitle("��¼");
            label.setText("Login");
        } catch (SQLException e) {
            //e.printStackTrace();
            flag=false;
            JOptionPane.showMessageDialog(null,"ID�ظ������飡");
        }
        return flag;

    }

    //ע���û�-0-���� 1-������

    public boolean SignIn1(JTextField tf1,JPasswordField tf2){

        String id = tf1.getText();
        String pass= String.valueOf(tf2.getPassword());
        AdminEdit ae=new AdminEdit();

        boolean flag=false;
        Connection conn = null;
        conn = DBUtil.getConn();

        CheckPerson ck=new CheckPerson();
        if (ae.checkId(id)) {

            System.out.println("ID�ظ�");
        }

        PreparedStatement pstmt=null;
        String sql=null;
        sql="insert into admin(id,pass,allowed) values(?,?,"+"'1'"+")";

        try {
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,id);
            pstmt.setString(2,pass);

            pstmt.executeUpdate();
            flag=true;
            JOptionPane.showMessageDialog(null,"ע�����Ա�ɹ�����ȴ���������Ա���");

        } catch (SQLException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null,"ID�ظ������飡");
            flag=false;
        }
        return flag;

    }



    //�������
    public boolean checkId(Admin ad) {

        boolean flag=false;
        Connection conn = null;
        conn = DBUtil.getConn();
        ResultSet rs = null;

        PreparedStatement pstmt=null;
        String sql=null;
        sql="select id from admin where id=?";

        try {
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,ad.getId());
            rs = pstmt.executeQuery();

            if (flag=rs.next()){
                System.out.println("ID�ظ�");
            }else flag=false;

        } catch (SQLException e) {

        }
        return flag;
    }
    //�������
    public boolean checkId(String str) {

        boolean flag=false;
        Connection conn = null;
        conn = DBUtil.getConn();
        ResultSet rs = null;

        PreparedStatement pstmt=null;
        String sql=null;
        sql="select id from person where id=?";

        try {
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,str);
            rs = pstmt.executeQuery();

            if (flag=rs.next()){
                System.out.println("ID�ظ�");
            }else flag=false;

        } catch (SQLException e) {
            //e.printStackTrace();
            //flag=false;
            //System.err.println("�������ʧ��");
        }
        return flag;
    }



    //���
    public boolean checkPAss(JTextField tf1,JPasswordField tf2) {

        String id = tf1.getText();
        String pass= String.valueOf(tf2.getPassword());

        boolean flag=false;
        Connection conn = null;
        conn = DBUtil.getConn();
        ResultSet rs = null;

        PreparedStatement pstmt=null;
        String sql=null;
        sql="select id from admin where id=? and pass=? and allowed='0'";

        try {
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,id);
            pstmt.setString(2,pass);

            rs = pstmt.executeQuery();

            if (flag=rs.next()){
                //JOptionPane.showMessageDialog(null,"��¼�ɹ�");
                //System.out.println("ID�ظ�");
                //System.out.println(sql);
            }else{
                flag=false;
                JOptionPane.showMessageDialog(null,"��¼ʧ�ܣ������˺ź�����");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
            //flag=false;
            //System.err.println("�������ʧ��");

        }
        return flag;
    }



}

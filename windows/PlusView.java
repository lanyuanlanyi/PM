package windows;

import JDBC.Person;

import tools.CheckPerson;
import tools.EditPerson;
import tools.ReFresh;
//import com.sun.java.util.jar.pack.Package;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by 85168 on 2017/3/23.
 */
public class PlusView {

    JFrame frame;
    Container container;
    public JTextField tf1;
    public JTextField tf2;
    public JComboBox<String> jcb3;
    public JComboBox<String> jcb4;
    public JTextField tf5;
    JComboBox<String> jcb6;
    JPanel panel6;
    JButton button1;

    ReFresh rf=new ReFresh();
    CheckPerson ck=new CheckPerson();
    EditPerson ep=new EditPerson();

    //DefaultTableModel model;

    //MainView mv;

    public PlusView() {

        frame=new JFrame("");

        container=frame.getContentPane();
        container.setLayout(new GridLayout(8,1));

        //标签
        JLabel label0=new JLabel("");
        JLabel label1=new JLabel("编号：");
        JLabel label2=new JLabel("姓名：");
        JLabel label3=new JLabel("性别：");
        JLabel label4=new JLabel("部门：");
        JLabel label5=new JLabel("工资：");
        JLabel label6=new JLabel("请选择查询条件：");

        String[] sex = {"","男","女"};    //初始选项
        String[] dep = {"","销售","领导","助理"};    //初始选项
        String[] choice = {"and","or"};

        //下拉框
        jcb3 = new JComboBox<String>(sex);    jcb3.setSelectedIndex(0);jcb3.setPreferredSize(new Dimension(173,30));
        jcb4 = new JComboBox<String>(dep);    jcb4.setSelectedIndex(0);jcb4.setPreferredSize(new Dimension(173,30));
        jcb6 = new JComboBox<String>(choice);    jcb6.setSelectedIndex(0);

        //文本框
        tf1 = new JTextField(10);
        tf2 = new JTextField(10);
        tf5 = new JTextField(10);

        //按钮
        button1=new JButton("确定");button1.setPreferredSize(new Dimension(100,35));
        JButton button2=new JButton("取消");button2.setPreferredSize(new Dimension(100,35));

        //格式
        label1.setFont(new Font("Dialog",1,20));
        label2.setFont(new Font("Dialog",1,20));
        label3.setFont(new Font("Dialog",1,20));
        label4.setFont(new Font("Dialog",1,20));
        label5.setFont(new Font("Dialog",1,20));
        label6.setFont(new Font("Dialog",1,20));
        tf1.setFont(new Font("Dialog",1,20));
        tf2.setFont(new Font("Dialog",1,20));
        jcb3.setFont(new Font("Dialog",1,20));
        jcb4.setFont(new Font("Dialog",1,20));
        tf5.setFont(new Font("Dialog",1,20));
        jcb6.setFont(new Font("Dialog",1,20));
        button1.setFont(new Font("Dialog",1,20));
        button2.setFont(new Font("Dialog",1,20));

        tf1.setPreferredSize(new Dimension(120,30));
        tf2.setPreferredSize(new Dimension(120,30));
        tf5.setPreferredSize(new Dimension(120,30));




        //面板
        JPanel panel0=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel1=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel2=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel3=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel4=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel5=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel6=new JPanel(new FlowLayout(FlowLayout.LEFT));//查询专有标明
        JPanel panel7=new JPanel(new FlowLayout(FlowLayout.CENTER));BoxLayout layout=new BoxLayout(panel7,BoxLayout.X_AXIS);
        JPanel []panels={panel0,panel1,panel2,panel3,panel4,panel5,panel6,panel7};

        //组建添加
        panel0.add(label0);
        panel1.add(label1);panel1.add(tf1);
        panel2.add(label2);panel2.add(tf2);
        panel3.add(label3);panel3.add(jcb3);
        panel4.add(label4);panel4.add(jcb4);
        panel5.add(label5);panel5.add(tf5);
        panel6.add(label6);panel6.add(jcb6);
        panel7.add(button1);panel7.add(button2);

        panel6.setVisible(false);

        for (int i = 0; i <8 ; i++)        container.add(panels[i],i);

        frame.setSize(320,500);
        frame.setLocation(888,100);

        //____________________________________________________________________
        //____________________________________________________________________
        //____________________________________________________________________


        ActionListener mal=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String str=e.getActionCommand();

                if ("确定".equals(str)){

                   /* if ("增加".equals(frame.getTitle())){

                        String id = tf1.getText();
                        if ("".equals(id)){
                            JOptionPane.showMessageDialog(null,"ID不能为空");
                            return;
                        }

                        Person person = null;
                        try {
                            person = ep.getPersonFromPV(tf1,tf2,jcb3,jcb4,tf5);
                        } catch (Exception e1) {
                            //e1.printStackTrace();
                            JOptionPane.showMessageDialog(null,"请检查格式");
                            return;
                        }

                        if (ep.insert(person))  JOptionPane.showMessageDialog(null,"添加信息成功，请刷新");
                        else JOptionPane.showMessageDialog(null,"ID重复，请检查");



                    }else if ("查询".equals(frame.getTitle())) {

                        ArrayList al=ck.getResult(tf1,tf2,jcb3,jcb4,tf5,jcb6);
                        ck.insert(al);
                        rf.tempSql();
                        JOptionPane.showMessageDialog(null,"查询到"+al.size()+"条结果，请刷新");

                        panel6.setVisible(false);
                    }

*/
                }else if ("取消".equals(str)){
                    frame.dispose();
                }

            }
        };


        //button1.addActionListener(mal);
        button2.addActionListener(mal);



    }

}

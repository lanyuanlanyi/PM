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

        //��ǩ
        JLabel label0=new JLabel("");
        JLabel label1=new JLabel("��ţ�");
        JLabel label2=new JLabel("������");
        JLabel label3=new JLabel("�Ա�");
        JLabel label4=new JLabel("���ţ�");
        JLabel label5=new JLabel("���ʣ�");
        JLabel label6=new JLabel("��ѡ���ѯ������");

        String[] sex = {"","��","Ů"};    //��ʼѡ��
        String[] dep = {"","����","�쵼","����"};    //��ʼѡ��
        String[] choice = {"and","or"};

        //������
        jcb3 = new JComboBox<String>(sex);    jcb3.setSelectedIndex(0);jcb3.setPreferredSize(new Dimension(173,30));
        jcb4 = new JComboBox<String>(dep);    jcb4.setSelectedIndex(0);jcb4.setPreferredSize(new Dimension(173,30));
        jcb6 = new JComboBox<String>(choice);    jcb6.setSelectedIndex(0);

        //�ı���
        tf1 = new JTextField(10);
        tf2 = new JTextField(10);
        tf5 = new JTextField(10);

        //��ť
        button1=new JButton("ȷ��");button1.setPreferredSize(new Dimension(100,35));
        JButton button2=new JButton("ȡ��");button2.setPreferredSize(new Dimension(100,35));

        //��ʽ
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




        //���
        JPanel panel0=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel1=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel2=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel3=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel4=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel5=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel6=new JPanel(new FlowLayout(FlowLayout.LEFT));//��ѯר�б���
        JPanel panel7=new JPanel(new FlowLayout(FlowLayout.CENTER));BoxLayout layout=new BoxLayout(panel7,BoxLayout.X_AXIS);
        JPanel []panels={panel0,panel1,panel2,panel3,panel4,panel5,panel6,panel7};

        //�齨���
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

                if ("ȷ��".equals(str)){

                   /* if ("����".equals(frame.getTitle())){

                        String id = tf1.getText();
                        if ("".equals(id)){
                            JOptionPane.showMessageDialog(null,"ID����Ϊ��");
                            return;
                        }

                        Person person = null;
                        try {
                            person = ep.getPersonFromPV(tf1,tf2,jcb3,jcb4,tf5);
                        } catch (Exception e1) {
                            //e1.printStackTrace();
                            JOptionPane.showMessageDialog(null,"�����ʽ");
                            return;
                        }

                        if (ep.insert(person))  JOptionPane.showMessageDialog(null,"�����Ϣ�ɹ�����ˢ��");
                        else JOptionPane.showMessageDialog(null,"ID�ظ�������");



                    }else if ("��ѯ".equals(frame.getTitle())) {

                        ArrayList al=ck.getResult(tf1,tf2,jcb3,jcb4,tf5,jcb6);
                        ck.insert(al);
                        rf.tempSql();
                        JOptionPane.showMessageDialog(null,"��ѯ��"+al.size()+"���������ˢ��");

                        panel6.setVisible(false);
                    }

*/
                }else if ("ȡ��".equals(str)){
                    frame.dispose();
                }

            }
        };


        //button1.addActionListener(mal);
        button2.addActionListener(mal);



    }

}

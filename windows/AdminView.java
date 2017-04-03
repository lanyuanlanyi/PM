package windows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 85168 on 2017/3/29.
 */
public class AdminView {

    public AdminView(){

        JFrame frame = new JFrame("Ȩ�޹���");

        Container container=frame.getContentPane();
        container.setLayout(new BorderLayout());
    //���
        JLabel label=new JLabel("Manager");

        //���
        String[] colsName = {"ID", "����", "Ȩ��"};
        DefaultTableModel model = new DefaultTableModel(colsName, 0);
        JTable table =new JTable(model);
        JScrollPane jsp = new JScrollPane(table);

        //��ť
        JButton button1=new JButton("����");
        JButton button2=new JButton("ɾ��");
        JButton button3=new JButton("�޸�");
        JButton button4=new JButton("ˢ��");



        //��ʽ����
        label.setPreferredSize(new Dimension(320,140));
        label.setFont(new Font("Dialog",1,50));
        label.setForeground(Color.gray);

        table.setRowHeight(25);
        table.setFont(new Font("Dialog",0,20));
        table.getTableHeader().setFont(new Font("Dialog",1,20));

        jsp.setPreferredSize(new Dimension(300,50));

        button1.setPreferredSize(new Dimension(100,35));
        button2.setPreferredSize(new Dimension(100,35));
        button3.setPreferredSize(new Dimension(100,35));
        //button4.setPreferredSize(new Dimension(100,35));

        button1.setFont(new Font("Dialog",1,20));
        button2.setFont(new Font("Dialog",1,20));
        button3.setFont(new Font("Dialog",1,20));
        //button4.setFont(new Font("Dialog",1,20));

        //�������
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        //panel.add(button4);

        //��������
        container.add(label, BorderLayout.NORTH);
        container.add(jsp,BorderLayout.CENTER);
        container.add(panel,BorderLayout.SOUTH);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320,500);
        frame.setLocation(888,100);

        frame.setVisible(true);
    }




}

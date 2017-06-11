package windows;

import tools.AdminEdit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 85168 on 2017/3/23.
 */
public class LoginView {

    JFrame frame;
    JLabel label;

    JTextField tf1;
    JPasswordField tf2;
    JCheckBox jcb1;
    JCheckBox jcb2;

    AdminEdit ae=new AdminEdit();

    public LoginView() {

        frame=new JFrame("登录");

        Container container=frame.getContentPane();
        container.setLayout(new BorderLayout());

        //所有组件-共9个
        label=new JLabel("Login ");

        final JLabel label1=new JLabel("帐号：");
        JLabel label2=new JLabel("密码：");

        tf1 = new JTextField("admin",12);
        tf2 = new JPasswordField("123",12);tf2.setEchoChar('*');

        jcb1=new JCheckBox("游客登录");
        jcb2=new JCheckBox("注册");
        jcb1.setSelected(true);

        JButton button1=new JButton("确定");
        JButton button2=new JButton("退出");

        //登录标签


        //格式
        label.setPreferredSize(new Dimension(320,140));
        tf1.setPreferredSize(new Dimension(270,35));
        tf2.setPreferredSize(new Dimension(270,35));
        button1.setPreferredSize(new Dimension(100,35));
        button2.setPreferredSize(new Dimension(100,35));

        label.setForeground(Color.gray);
        label.setFont(new Font("Dialog",1,50));
        label1.setFont(new Font("Dialog",1,20));
        label2.setFont(new Font("Dialog",1,20));
        tf1.setFont(new Font("Dialog",1,20));
        tf2.setFont(new Font("Dialog",1,20));
        jcb1.setFont(new Font("Dialog",1,20));
        jcb2.setFont(new Font("Dialog",1,20));

        button1.setFont(new Font("Dialog",1,20));
        button2.setFont(new Font("Dialog",1,20));



        JPanel panel1=new JPanel();
        BoxLayout layout=new BoxLayout(panel1,BoxLayout.X_AXIS);
        JPanel panel2=new JPanel();
        panel2.setPreferredSize(new Dimension(320,70));
        BoxLayout layout2=new BoxLayout(panel2,BoxLayout.X_AXIS);

        panel1.add(label1,0);panel1.add(tf1,1);
        panel1.add(label2,2);panel1.add(tf2,3);

        panel1.add(jcb1,4);panel1.add(jcb2,5);

        panel2.add(button1,0);panel2.add(button2,1);


        container.add(label, BorderLayout.NORTH);
        container.add(panel1,BorderLayout.CENTER);
        container.add(panel2,BorderLayout.SOUTH);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320,500);
        frame.setLocation(100,100);

        frame.setVisible(true);

        ActionListener lal=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String str=e.getActionCommand();
                if ("确定".equals(str)){

                    ae.login(frame,label,tf1,tf2,jcb1,jcb2);

                }else if ("退出".equals(str)){
                    frame.dispose();
                }


            }
        };

        button1.addActionListener(lal);
        button2.addActionListener(lal);

/*      原lsl监听器----现使用lambda表达式进行替换以下
        ActionListener lsl=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (jcb2.isSelected()){
                    frame.setTitle("注册");
                    label.setText("Sign");
                }else{
                    frame.setTitle("登录");
                    label.setText("Login");
                }

            }
        };

        jcb2.addActionListener(lsl);
        */

        jcb2.addActionListener((e) -> {

            if (jcb2.isSelected()) {
                frame.setTitle("注册");
                label.setText("Sign");
            } else {
                frame.setTitle("登录");
                label.setText("Login");
            }
        });





    }

    public static void main(String[] args) {
        new LoginView();
    }
}

package windows;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Created by 85168 on 2017/3/28.
 */
class ChatView {        //修改IP在MainView的326行

    public int i = (int) (Math.random() * 100);
    Socket s;
    JFrame frame;
    JTextArea ajTextArea;
    JTextField ajTextField;

    public ChatView(Socket s) {
        this.s = s;


        frame = new JFrame("" + i);
        Container container;
        container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        ajTextArea = new JTextArea("讨论区域\n========================", 10, 10);
        ajTextArea.setLineWrap(true);
        ajTextField = new JTextField(null, 13);
        JButton ajButton = new JButton("发送");
        JScrollPane asp = new JScrollPane(ajTextArea);
        //格式
        ajTextArea.setEditable(false);
        ajTextArea.setFont(new Font("Dialog", 1, 20));
        ajTextField.setFont(new Font("Dialog", 1, 14));
        ajButton.setFont(new Font("Dialog", 1, 14));

        ajTextField.setPreferredSize(new Dimension(100, 30));
        ajButton.setPreferredSize(new Dimension(70, 30));
        asp.setPreferredSize(new Dimension(270, 330));

        //添加
        JPanel apanel = new JPanel();
        BoxLayout layout = new BoxLayout(apanel, BoxLayout.X_AXIS);
        apanel.add(ajTextField);
        apanel.add(ajButton);

        container.add(asp, BorderLayout.CENTER);
        container.add(apanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        frame.setSize(320, 500);
        frame.setLocation(100, 100);
        frame.setVisible(true);

        ActionListener mal = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String str = e.getActionCommand();
                if ("发送".equals(str)) {

                    try {
                        //外部类成员
                        PrintWriter pw = new PrintWriter(ChatView.this.s.getOutputStream());

                        String msg = ajTextField.getText();
                        pw.println("管理员"+i+":\n   "+msg);
                        pw.flush();
                        ajTextField.setText("");


                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }

            }
        };
        //监听注册
        ajButton.addActionListener(mal);
        new ClientThread(this,this.s).start();
    }

}

//接收服务器发来的数据并更新文本域
class ClientThread extends Thread{

    ChatView cv;
    Socket s;

    public ClientThread(ChatView cv, Socket s) {
        this.cv = cv;
        this.s = s;
    }

    @Override
    public void run() {
        super.run();

        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));

            while (true){
                String msg=br.readLine();
                cv.ajTextArea.setText(cv.ajTextArea.getText()+msg+"\n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}


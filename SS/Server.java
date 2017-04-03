package SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 *
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(8000);

        ArrayList<Socket> al=new ArrayList<Socket>();
        while(true){

            System.out.println("等待客户机接入...");
            Socket s=ss.accept();
            String ip=s.getInetAddress().getHostAddress();
            int port=s.getPort();

            System.out.println("客户机1"+ip+"--"+port);

            al.add(s);

            new ServerThread(al,s).start();



        }
    }


}

class ServerThread extends Thread{

    //成员
    ArrayList<Socket> al;
    Socket s;

    public ServerThread(ArrayList al, Socket s) {
        this.al = al;
        this.s = s;
    }

    //线程执行体
    @Override
    public void run() {
        super.run();

        try {
            //获取客户机s的输入流
            BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));

            while (true){

                String msg=br.readLine();

                for (Socket s1:al){

                    PrintWriter pw=new PrintWriter(s1.getOutputStream());
                    pw.println(msg);
                    pw.flush();

                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }

    }


}

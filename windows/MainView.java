package windows;

import JDBC.Person;
import SS.Server;
import org.dom4j.DocumentException;
import tools.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by 85168 on 2017/3/23.
 */
public class MainView {

    public  DefaultTableModel model;
    public JTable table;
    public JFrame frame;
    public  Container container;
    public JMenuBar bar;
    public JMenu plus;
    Person ppersonmodify;
    Person persontemp;

    public JButton button1;
    public JButton button2;
    public JButton button3;

    public JMenuItem item1;
    public JMenuItem item2;
    public JMenuItem item3;
    public JMenuItem item5;
    public JMenuItem item6;

    PlusView pv=new PlusView();
    ModifyModel mm=new ModifyModel();
    SortTable st=new SortTable();
    ReFresh rf=new ReFresh();
    EditPerson ep=new EditPerson();
    CheckPerson cp=new CheckPerson();
    WritrteToFile wtf=new WritrteToFile();
    ReadFile readFile=new ReadFile();
    CheckPerson ck=new CheckPerson();

    public MainView() {

        frame = new JFrame("���¹���ϵͳ");

        container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        JLabel label = new JLabel("���¹���ϵͳ");
        //main����
        button1 = new JButton("����");
        button2 = new JButton("ɾ��");
        button3 = new JButton("�޸�");
        JButton button4 = new JButton("��ѯ");
        JButton button5 = new JButton("ˢ��");
        JButton[] btns = {button1, button2, button3, button4, button5};

        //���
        String[] colsName = {"���", "����", "�Ա�", "����", "����"};
        //JTable table = new JTable(model);
        model = new DefaultTableModel(colsName, 0);
        table =new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Dialog",0,20));
        //table.setTableHeader();
        table.getTableHeader().setFont(new Font("Dialog",1,20));
        //table.setPreferredSize(new Dimension(370,370));
        JScrollPane jsp = new JScrollPane(table);jsp.setPreferredSize(new Dimension(435,330));
        //�����˵���
        bar = new JMenuBar();
        JMenu file = new JMenu("�ļ�");
        JMenu help = new JMenu("����");

        plus=new JMenu("����");
        //JMenuItem adminManage = new JMenuItem("Ȩ�޹���");
        //JMenuItem depManage= new JMenuItem("���Ź���");
        JMenuItem discuss = new JMenuItem("��������");




        item1 = new JMenuItem("����");
        item2 = new JMenuItem("�޸�");
        item3 = new JMenuItem("ɾ��");
        JMenuItem item4 = new JMenuItem("��ѯ");
        item5 = new JMenuItem("�����ļ�");
        item6 = new JMenuItem("�����ļ�");
        JMenuItem item7 = new JMenuItem("�뿪");
        JMenuItem item8 = new JMenuItem("����");
        JMenuItem item9 = new JMenuItem("ע��");


        bar.add(file);
        bar.add(plus);
        bar.add(help);

        file.add(item1);
        file.add(item2);
        file.add(item3);
        file.add(item4);
        file.addSeparator();
        file.add(item5);
        file.add(item6);
        file.addSeparator();
        file.add(item9);
        file.add(item7);

        //plus.add(adminManage);
        //plus.add(depManage);
        //plus.addSeparator();
        plus.add(discuss);

        help.add(item8);

        frame.setJMenuBar(bar);

        //��ʽ
        label.setFont(new Font("Dialog",1,24));

        button1.setFont(new Font("Dialog",1,20));
        button2.setFont(new Font("Dialog",1,20));
        button3.setFont(new Font("Dialog",1,20));
        button4.setFont(new Font("Dialog",1,20));
        button5.setFont(new Font("Dialog",1,20));
        file.setFont(new Font("Dialog",1,20));
        help.setFont(new Font("Dialog",1,20));
        plus.setFont(new Font("Dialog",1,20));


        //���
        final JPanel panel1 = new JPanel();
        final JPanel panel2 = new JPanel();//panel2.setPreferredSize(new Dimension(370,370));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());

        //BoxLayout layout=new BoxLayout(panel3,BoxLayout.X_AXIS);


        panel1.add(label);
        panel2.add(jsp);

        for (JButton btn : btns) panel3.add(btn);
        //BoxLayout layout=new BoxLayout(panel3,BoxLayout.Y_AXIS);

        container.add(panel1, BorderLayout.NORTH);
        container.add(panel2, BorderLayout.CENTER);
        container.add(panel3, BorderLayout.SOUTH);


//========================
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocation(404, 100);
        frame.setVisible(true);

        mm.WriteModel(rf.getAll(),model);//    ��ȡ-д��Model

        ActionListener mal = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String str = e.getActionCommand();


                if ("����".equals(str)) {

                    pv.panel6.setVisible(false);//�������

                    pv.frame.setTitle("����");
                    pv.frame.setVisible(true);



                } else if ("ɾ��".equals(str)) {

                    ArrayList<Person> al=mm.getSelectedPresons(table,model);
                    if (al.size()==0){
                        JOptionPane.showMessageDialog(null,"��ѡ����Ҫɾ������");
                        return;
                    }
                    ep.deleteAll(al);

                    JOptionPane.showMessageDialog(null,"��ɾ��"+al.size()+"����¼");

                    //ˢ�±�
                    model.setRowCount(0);//���Model
                    mm.WriteModel(rf.getAll(),model);//    ��ȡ-д��Model
                    //�����Ӵ��ڲ���ʾ
                    pv.panel6.setVisible(false);
                    pv.frame.setVisible(false);

                } else if ("�޸�".equals(str)) {

                 //�����Ӵ��ڲ���ʾ
                    pv.panel6.setVisible(false);
                    pv.frame.setVisible(false);
                    //ɾ��������
                    ep.delete(persontemp);

                    if (cp.checkId(ppersonmodify)) {
                            ep.insert(persontemp);
                            //System.out.println("ID�ظ����޸�ʧ��");
                            JOptionPane.showMessageDialog(null,"ID�ظ����޸�ʧ��");
                        }else  if(ep.isSame(persontemp,ppersonmodify)){
                            JOptionPane.showMessageDialog(null,"δ�����ģ�����");
                            System.out.println(persontemp+"\n"+ppersonmodify);
                            ep.insert(persontemp);

                        }else if (!cp.checkId(ppersonmodify)) {

                            ep.insert(ppersonmodify);
                            //System.out.println("�޸ĳɹ�");
                            JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
                        }
                        //ˢ��
                    model.setRowCount(0);//���Model
                    mm.WriteModel(rf.getAll(),model);//    ��ȡ-д��Model

                } else if ("��ѯ".equals(str)) {
                    pv.frame.setTitle("��ѯ");
                    pv.panel6.setVisible(true);//�������
                    pv.frame.setVisible(true);

                } else if ("ˢ��".equals(str)) {

                    model.setRowCount(0);//���Model
                    mm.WriteModel(rf.getAll(),model);//    ��ȡ-д��Model
                    //�жϲ�ѯ������Ƿ�С��ȫ��С���򻻻���
                    if (!rf.personBig()) {
                        rf.tempSql();
                        rf.cleanResult();
                    }
                    //�����Ӵ��ڲ���ʾ
                    pv.panel6.setVisible(false);
                    pv.frame.setVisible(false);
                }else if("�����ļ�".equals(str)){

                    //ѡ���ļ�
                    JFileChooser chooser = new JFileChooser("D:\\work\\PM\\src");
                    chooser.showOpenDialog(null);
                    //��ȡ��ѡ�ļ�
                    File file  = chooser.getSelectedFile();

                    ArrayList<Person>al=new ArrayList<Person>();


                    //���ļ��õ�al-д��
                    //File file=new File("D:\\work\\PM\\src\\JDBC\\test1.xml");
                    try {
                        al=readFile.readFromXML(file);
                        model.setRowCount(0);//���Model
                        mm.writeModel(al,model);
                    } catch (DocumentException e1) {
                        e1.printStackTrace();
                    }
                    int x=JOptionPane.showConfirmDialog(null,"�Ƿ񽫸ñ��뵽���ݿ⣿");
                    //��al���в��ز���-���ظ�ֵ����-�Ƴ���ǰ����bl
                    //�Ƿ񽫵�ǰ��������д�����ݿ�
                    if (x==0) {
                        readFile.writeToSql(al);
                        model.setRowCount(0);//���Model
                        mm.WriteModel(rf.getAll(),model);

                    }else if (x==1 || x==2){
                        JOptionPane.showMessageDialog(null,"��ȡ��д��");
                        return;
                    }


                }else if("�����ļ�".equals(str)){

                    //����tableΪ�յ����
                    ArrayList<Person> al;
                    if (table.getSelectedRow()==-1){
                        al=mm.getTablePresons(table,model);

                        System.out.println(al.size());
                    }else            al=mm.getSelectedPresons(table,model);

                    JFileChooser chooser = new JFileChooser("D:\\work\\PM\\src");
                    chooser.showSaveDialog(null);
                    File file = chooser.getSelectedFile();

                    try {
                        wtf.writeToSQL(file,al);
                        JOptionPane.showMessageDialog(null,"�����ļ��ɹ�[��"+al.size()+"����Ϣ����]\n"+file);
                    } catch (Exception e1) {
                        //JOptionPane.showMessageDialog(null,"����ʧ�ܣ�����");
                        return;
                    }

                }else if("�뿪".equals(str)){
                    frame.dispose();
                    System.exit(0);
                }else if("ע��".equals(str)){
                    frame.dispose();
                    new LoginView();
                    //System.exit(0);


                }
                else if("����".equals(str)){
                    //System.out.println("����ʿ�������վ");
                    JOptionPane.showMessageDialog(null,"����ʿ�������վ:www.msdn.com");
                }else if ("��������".equals(str)){

                    Socket s= null;
                    try {
                        s = new Socket(InetAddress.getByName("192.168.2.137"),8000);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    new ChatView(s);

                }else if ("ȷ��".equals(str)){

                    String STR=pv.frame.getTitle();
                    if (STR.equals("����")){

                        pv.panel6.setVisible(false);

                        String id =pv.tf1.getText();
                        if ("".equals(id)){
                            JOptionPane.showMessageDialog(null,"ID����Ϊ��");
                            return;
                        }

                        Person person = null;
                        try {
                            person = ep.getPersonFromPV(pv.tf1,pv.tf2,pv.jcb3,pv.jcb4,pv.tf5);
                        } catch (Exception e1) {
                            //e1.printStackTrace();
                            JOptionPane.showMessageDialog(null,"�����ʽ");
                            return;
                        }

                        if (ep.insert(person))  JOptionPane.showMessageDialog(null,"�����Ϣ�ɹ�");
                        else JOptionPane.showMessageDialog(null,"ID�ظ�������");

                        model.setRowCount(0);//���Model
                        mm.WriteModel(rf.getAll(),model);


                    }
                    if (STR.equals("��ѯ")){

                        pv.panel6.setVisible(true);

                        ArrayList al=ck.getResult(pv.tf1,pv.tf2,pv.jcb3,pv.jcb4,pv.tf5,pv.jcb6);
                        /*ck.insert(al);
                        rf.tempSql();*/
                        //System.out.println(al.size());
                        if (al.size()!=0){
                            model.setRowCount(0);
                            mm.WriteModel(al,model);
                        }

                        JOptionPane.showMessageDialog(null,"��ѯ��"+al.size()+"�����");

                        pv.panel6.setVisible(false);

                    }


                }

            }
        };
        //�󶨼���
        for (JButton btn : btns) btn.addActionListener(mal);
        item1.addActionListener(mal);
        item2.addActionListener(mal);
        item3.addActionListener(mal);
        item4.addActionListener(mal);
        item5.addActionListener(mal);
        item6.addActionListener(mal);
        item7.addActionListener(mal);
        item8.addActionListener(mal);
        item9.addActionListener(mal);

        //plus.addActionListener(mal);

        //adminManage.addActionListener(mal);
        //depManage.addActionListener(mal);
        discuss.addActionListener(mal);
        pv.button1.addActionListener(mal);


//��������-�������
        MouseListener mml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Point mp = e.getPoint();
                String str = table.getColumnName(table.columnAtPoint(mp));

                if ("���".equals(str)) {         st.sortId(model);
                } else if ("����".equals(str)) {        st.sortName(model);
                } else if ("�Ա�".equals(str)) {          st.sortSex(model);
                } else if ("����".equals(str)) {          st.sortDep(model);
                } else if ("����".equals(str)) {          st.sortSal(model);
                }
            }
            @Override            public void mousePressed(MouseEvent e) {}
            @Override            public void mouseReleased(MouseEvent e) {}
            @Override            public void mouseEntered(MouseEvent e) {}
            @Override            public void mouseExited(MouseEvent e) {}

        };
        table.getTableHeader().addMouseListener(mml);

//��-������==================
// =========================
        MouseListener mtl = new MouseListener() {
//�Ĵ���
            ArrayList<Person> al=new ArrayList<Person>();
            ArrayList bl=new ArrayList();
            ArrayList cl=new ArrayList();

            @Override
            public void mouseClicked(MouseEvent e) {
//����Ĵ���������ݵ����
                Point mp = e.getPoint();
                mm.mainToPlus(mp,table,pv);

                int i=table.getSelectedRow();
                ppersonmodify=mm.getSelectedPreson(table,model);
                int id=mm.getSelectedId(table,model);
//ѡ�в��޸Ķ������
                try {
                    if (i==(Integer) bl.get(0) && persontemp!=null)   id=(int)cl.get(0);
                    else if(i!=(Integer) bl.get(0)&& persontemp==null)   id=mm.getSelectedId(table,model);
                } catch (IndexOutOfBoundsException e1) {}

                persontemp=ep.getPersonFromId(id);

                if (persontemp==null) {
                    persontemp = al.get(0);//������IDʱ��֤persontempΪ֮ǰֵ����
                    id=persontemp.getId();
                }
                al.add(0,persontemp);
                bl.add(0,i);
                cl.add(0,id);
                //System.out.println(al);
                //System.out.println(id+"<temp>"+persontemp);
                //System.out.println(i+"<modify>"+ppersonmodify);
            }
            @Override            public void mousePressed(MouseEvent e) {}
            @Override            public void mouseReleased(MouseEvent e) {}
            @Override            public void mouseEntered(MouseEvent e) {}
            @Override            public void mouseExited(MouseEvent e) {}
        };
        //�����ı�������
        table.addMouseListener(mtl);
    }


}

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

        frame = new JFrame("人事管理系统");

        container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        JLabel label = new JLabel("人事管理系统");
        //main按键
        button1 = new JButton("增加");
        button2 = new JButton("删除");
        button3 = new JButton("修改");
        JButton button4 = new JButton("查询");
        JButton button5 = new JButton("刷新");
        JButton[] btns = {button1, button2, button3, button4, button5};

        //表格
        String[] colsName = {"编号", "姓名", "性别", "部门", "工资"};
        //JTable table = new JTable(model);
        model = new DefaultTableModel(colsName, 0);
        table =new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Dialog",0,20));
        //table.setTableHeader();
        table.getTableHeader().setFont(new Font("Dialog",1,20));
        //table.setPreferredSize(new Dimension(370,370));
        JScrollPane jsp = new JScrollPane(table);jsp.setPreferredSize(new Dimension(435,330));
        //创建菜单栏
        bar = new JMenuBar();
        JMenu file = new JMenu("文件");
        JMenu help = new JMenu("帮助");

        plus=new JMenu("讨论");
        //JMenuItem adminManage = new JMenuItem("权限管理");
        //JMenuItem depManage= new JMenuItem("部门管理");
        JMenuItem discuss = new JMenuItem("问题讨论");




        item1 = new JMenuItem("增加");
        item2 = new JMenuItem("修改");
        item3 = new JMenuItem("删除");
        JMenuItem item4 = new JMenuItem("查询");
        item5 = new JMenuItem("导入文件");
        item6 = new JMenuItem("导出文件");
        JMenuItem item7 = new JMenuItem("离开");
        JMenuItem item8 = new JMenuItem("帮助");
        JMenuItem item9 = new JMenuItem("注销");


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

        //格式
        label.setFont(new Font("Dialog",1,24));

        button1.setFont(new Font("Dialog",1,20));
        button2.setFont(new Font("Dialog",1,20));
        button3.setFont(new Font("Dialog",1,20));
        button4.setFont(new Font("Dialog",1,20));
        button5.setFont(new Font("Dialog",1,20));
        file.setFont(new Font("Dialog",1,20));
        help.setFont(new Font("Dialog",1,20));
        plus.setFont(new Font("Dialog",1,20));


        //面板
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

        mm.WriteModel(rf.getAll(),model);//    读取-写入Model

        ActionListener mal = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String str = e.getActionCommand();


                if ("增加".equals(str)) {

                    pv.panel6.setVisible(false);//标明语句

                    pv.frame.setTitle("增加");
                    pv.frame.setVisible(true);



                } else if ("删除".equals(str)) {

                    ArrayList<Person> al=mm.getSelectedPresons(table,model);
                    if (al.size()==0){
                        JOptionPane.showMessageDialog(null,"请选中想要删除的行");
                        return;
                    }
                    ep.deleteAll(al);

                    JOptionPane.showMessageDialog(null,"已删除"+al.size()+"条记录");

                    //刷新表
                    model.setRowCount(0);//清空Model
                    mm.WriteModel(rf.getAll(),model);//    读取-写入Model
                    //设置子窗口不显示
                    pv.panel6.setVisible(false);
                    pv.frame.setVisible(false);

                } else if ("修改".equals(str)) {

                 //设置子窗口不显示
                    pv.panel6.setVisible(false);
                    pv.frame.setVisible(false);
                    //删除后增加
                    ep.delete(persontemp);

                    if (cp.checkId(ppersonmodify)) {
                            ep.insert(persontemp);
                            //System.out.println("ID重复，修改失败");
                            JOptionPane.showMessageDialog(null,"ID重复，修改失败");
                        }else  if(ep.isSame(persontemp,ppersonmodify)){
                            JOptionPane.showMessageDialog(null,"未作更改，请检查");
                            System.out.println(persontemp+"\n"+ppersonmodify);
                            ep.insert(persontemp);

                        }else if (!cp.checkId(ppersonmodify)) {

                            ep.insert(ppersonmodify);
                            //System.out.println("修改成功");
                            JOptionPane.showMessageDialog(null,"修改成功");
                        }
                        //刷新
                    model.setRowCount(0);//清空Model
                    mm.WriteModel(rf.getAll(),model);//    读取-写入Model

                } else if ("查询".equals(str)) {
                    pv.frame.setTitle("查询");
                    pv.panel6.setVisible(true);//标明语句
                    pv.frame.setVisible(true);

                } else if ("刷新".equals(str)) {

                    model.setRowCount(0);//清空Model
                    mm.WriteModel(rf.getAll(),model);//    读取-写入Model
                    //判断查询结果表是否小于全表，小于则换回来
                    if (!rf.personBig()) {
                        rf.tempSql();
                        rf.cleanResult();
                    }
                    //设置子窗口不显示
                    pv.panel6.setVisible(false);
                    pv.frame.setVisible(false);
                }else if("导入文件".equals(str)){

                    //选择文件
                    JFileChooser chooser = new JFileChooser("D:\\work\\PM\\src");
                    chooser.showOpenDialog(null);
                    //获取所选文件
                    File file  = chooser.getSelectedFile();

                    ArrayList<Person>al=new ArrayList<Person>();


                    //从文件得到al-写入
                    //File file=new File("D:\\work\\PM\\src\\JDBC\\test1.xml");
                    try {
                        al=readFile.readFromXML(file);
                        model.setRowCount(0);//清空Model
                        mm.writeModel(al,model);
                    } catch (DocumentException e1) {
                        e1.printStackTrace();
                    }
                    int x=JOptionPane.showConfirmDialog(null,"是否将该表导入到数据库？");
                    //对al进行查重操作-将重复值返回-移除当前对象到bl
                    //是否将当前表内数据写入数据库
                    if (x==0) {
                        readFile.writeToSql(al);
                        model.setRowCount(0);//清空Model
                        mm.WriteModel(rf.getAll(),model);

                    }else if (x==1 || x==2){
                        JOptionPane.showMessageDialog(null,"已取消写入");
                        return;
                    }


                }else if("导出文件".equals(str)){

                    //考虑table为空的情况
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
                        JOptionPane.showMessageDialog(null,"导出文件成功[共"+al.size()+"条信息导出]\n"+file);
                    } catch (Exception e1) {
                        //JOptionPane.showMessageDialog(null,"导出失败，请检查");
                        return;
                    }

                }else if("离开".equals(str)){
                    frame.dispose();
                    System.exit(0);
                }else if("注销".equals(str)){
                    frame.dispose();
                    new LoginView();
                    //System.exit(0);


                }
                else if("帮助".equals(str)){
                    //System.out.println("请访问开发者网站");
                    JOptionPane.showMessageDialog(null,"请访问开发者网站:www.msdn.com");
                }else if ("问题讨论".equals(str)){

                    Socket s= null;
                    try {
                        s = new Socket(InetAddress.getByName("192.168.2.137"),8000);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    new ChatView(s);

                }else if ("确定".equals(str)){

                    String STR=pv.frame.getTitle();
                    if (STR.equals("增加")){

                        pv.panel6.setVisible(false);

                        String id =pv.tf1.getText();
                        if ("".equals(id)){
                            JOptionPane.showMessageDialog(null,"ID不能为空");
                            return;
                        }

                        Person person = null;
                        try {
                            person = ep.getPersonFromPV(pv.tf1,pv.tf2,pv.jcb3,pv.jcb4,pv.tf5);
                        } catch (Exception e1) {
                            //e1.printStackTrace();
                            JOptionPane.showMessageDialog(null,"请检查格式");
                            return;
                        }

                        if (ep.insert(person))  JOptionPane.showMessageDialog(null,"添加信息成功");
                        else JOptionPane.showMessageDialog(null,"ID重复，请检查");

                        model.setRowCount(0);//清空Model
                        mm.WriteModel(rf.getAll(),model);


                    }
                    if (STR.equals("查询")){

                        pv.panel6.setVisible(true);

                        ArrayList al=ck.getResult(pv.tf1,pv.tf2,pv.jcb3,pv.jcb4,pv.tf5,pv.jcb6);
                        /*ck.insert(al);
                        rf.tempSql();*/
                        //System.out.println(al.size());
                        if (al.size()!=0){
                            model.setRowCount(0);
                            mm.WriteModel(al,model);
                        }

                        JOptionPane.showMessageDialog(null,"查询到"+al.size()+"条结果");

                        pv.panel6.setVisible(false);

                    }


                }

            }
        };
        //绑定监听
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


//鼠标监听类-排序操作
        MouseListener mml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Point mp = e.getPoint();
                String str = table.getColumnName(table.columnAtPoint(mp));

                if ("编号".equals(str)) {         st.sortId(model);
                } else if ("姓名".equals(str)) {        st.sortName(model);
                } else if ("性别".equals(str)) {          st.sortSex(model);
                } else if ("部门".equals(str)) {          st.sortDep(model);
                } else if ("工资".equals(str)) {          st.sortSal(model);
                }
            }
            @Override            public void mousePressed(MouseEvent e) {}
            @Override            public void mouseReleased(MouseEvent e) {}
            @Override            public void mouseEntered(MouseEvent e) {}
            @Override            public void mouseExited(MouseEvent e) {}

        };
        table.getTableHeader().addMouseListener(mml);

//新-表格监听==================
// =========================
        MouseListener mtl = new MouseListener() {
//寄存器
            ArrayList<Person> al=new ArrayList<Person>();
            ArrayList bl=new ArrayList();
            ArrayList cl=new ArrayList();

            @Override
            public void mouseClicked(MouseEvent e) {
//点击寄存操作，备份点击行
                Point mp = e.getPoint();
                mm.mainToPlus(mp,table,pv);

                int i=table.getSelectedRow();
                ppersonmodify=mm.getSelectedPreson(table,model);
                int id=mm.getSelectedId(table,model);
//选中并修改多个处理
                try {
                    if (i==(Integer) bl.get(0) && persontemp!=null)   id=(int)cl.get(0);
                    else if(i!=(Integer) bl.get(0)&& persontemp==null)   id=mm.getSelectedId(table,model);
                } catch (IndexOutOfBoundsException e1) {}

                persontemp=ep.getPersonFromId(id);

                if (persontemp==null) {
                    persontemp = al.get(0);//当更改ID时保证persontemp为之前值不变
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
        //单独的表格监听类
        table.addMouseListener(mtl);
    }


}

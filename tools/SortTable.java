package tools;

import JDBC.Person;
import compare.*;


import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 85168 on 2017/3/25.
 */
public class SortTable {

    boolean flag = true;//变序符号

    //排序操作

    //薪水排序
    public void sortSal(DefaultTableModel model) {

        //ArrayList<Person> al = new ArrayList<Person>();//
        ModifyModel mm = new ModifyModel();
        ArrayList<Person> al = mm.readModel(model);

        CompareSal compare = new CompareSal();
        Collections.sort(al, compare);

        flag = !flag;
        if (flag) mm.writeModel(al, model);
        else mm.WriteModel(al, model);

        //System.out.println("排序成功--");
    }


    //______ID___________
//==============================================
    public void sortId(DefaultTableModel model) {

        ModifyModel mm = new ModifyModel();
        ArrayList<Person> al = mm.readModel(model);

        CompareId compare = new CompareId();
        Collections.sort(al, compare);

        flag = !flag;
        if (flag) mm.writeModel(al, model);
        else mm.WriteModel(al, model);

        //System.out.println("排序成功--");
    }
//______NAME___________
//==============================================

    public void sortName(DefaultTableModel model) {

        ModifyModel mm = new ModifyModel();
        ArrayList<Person> al = mm.readModel(model);

        CompareName compare = new CompareName();
        Collections.sort(al, compare);

        flag = !flag;
        if (flag) mm.writeModel(al, model);
        else mm.WriteModel(al, model);

        //System.out.println("排序成功--");
    }
//______SEX___________
//==============================================


    public void sortSex(DefaultTableModel model) {

        ModifyModel mm = new ModifyModel();
        ArrayList<Person> al = mm.readModel(model);

        CompareSex compare = new CompareSex();
        Collections.sort(al, compare);

        flag = !flag;
        if (flag) mm.writeModel(al, model);
        else mm.WriteModel(al, model);

        //System.out.println("排序成功--");
    }
//______DEP___________
//==============================================


    public void sortDep(DefaultTableModel model) {

        ModifyModel mm = new ModifyModel();
        ArrayList<Person> al = mm.readModel(model);

        CompareDep compare = new CompareDep();
        Collections.sort(al, compare);

        flag = !flag;
        if (flag) mm.writeModel(al, model);
        else mm.WriteModel(al, model);

        //System.out.println("排序成功--");
    }

}














package Tesst;

import windows.LoginView;

import javax.swing.*;

/**
 * Created by 85168 on 2017/3/23.
 */
public class run {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        new LoginView();
    }
}

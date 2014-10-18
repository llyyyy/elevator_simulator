package gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by ksh on 2014-05-24.
 */
public class Gui extends Frame implements WindowListener {

    public Gui() {
    }

    public void showGui() {
        init();
    }

    private void init() {
        setSize(600, 600);
        setVisible(true);
        setLayout(null);

     //   createMenu();

        addWindowListener(this);

        Panel panel = new Buliding("1b", 8, 20, 70);
        add(panel);
    }

    public void createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu settingMenu = new Menu("환경설정");
        settingMenu.add("설정");

        menuBar.add(settingMenu);

        setMenuBar(menuBar);

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

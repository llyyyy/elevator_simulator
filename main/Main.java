package main;

import gui.Gui;
import middle.InputBuffer;
import process.Process;

/**
 * Created by ksh on 2014-05-24.
 */
public class Main {
    private InputBuffer inputBuffer;
    private Gui gui;
    private process.Process process;

    public Main() {
        inputBuffer = InputBuffer.getInstance();
        process = new Process();
        gui = new Gui();
    }

    public static void main(String[] args) {
        new Main().mainProcess();
    }

    public void mainProcess() {
        gui.showGui();
        process.process();
    }
}

package gui;

import middle.InputBuffer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Created by ksh on 2014-05-24.
*/
class FloorBtnPanel extends Panel implements ActionListener {
    private String ID = null;
    public final static int HEIGHT = 57;
    public final static int WIDTH = 30;
    private static InputBuffer inputBuffer = InputBuffer.getInstance();
    private Button upBtn;
    private Button downBtn;

    public FloorBtnPanel(String ID, int x, int y) {
        this.ID = ID;
        setBounds(x, y, WIDTH, HEIGHT);
        setBackground(Color.GRAY);
        setLayout(null);

        upBtn = new Button("△");
        upBtn.addActionListener(this);
        upBtn.setBounds(6, 7, 20, 20);
        upBtn.setVisible(true);
        add(upBtn);

        downBtn = new Button("▽");
        downBtn.addActionListener(this);
        downBtn.setBounds(6, 32, 20, 20);
        add(downBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int floor = getFloor();
        String command = e.getActionCommand();
        if("△".equals(command)){
            inputBuffer.selectUpDown(floor, "UP");
            upBtn.setLabel("▲");
        }else if("▽".equals(command)){
            inputBuffer.selectUpDown(floor, "DOWN");
            downBtn.setLabel("▼");
        }

    }

    private int getFloor(){
        String[] str = ID.split("_|f");
        int result = Integer.parseInt(str[1]);
        return result;
    }

    public void toggleBtn(String upDown){
        upBtn.setLabel("△");
        downBtn.setLabel("▽");
        if(upDown.equals("UP")){
            upBtn.setLabel("▲");
        }else if(upDown.equals("DOWN")){
            downBtn.setLabel("▼");
        }
    }
    public void toggleBtn(){
        upBtn.setLabel("△");
        downBtn.setLabel("▽");
    }
}

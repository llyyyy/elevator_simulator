package gui;

import middle.InputBuffer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by ksh on 2014-05-24.
 */
class ElevatorBtnPanel extends Panel implements ActionListener {
    private final int BTN_SIZE = 20;
    private String ID = null;
    private static InputBuffer inputBuffer = InputBuffer.getInstance();
    private Map<Integer, Button> btns;

    public ElevatorBtnPanel(String ID, int x, int y, int numOfFloor) {
        this.ID = ID;
        int maxWidthCnt = 3;
        int panelWidth = (BTN_SIZE + 2) * maxWidthCnt;
        int panelHeight = (BTN_SIZE + 2) * ((numOfFloor / 4) + 1);
        setBounds(x, y, panelWidth, panelHeight);
        setBackground(Color.GRAY);
        setLayout(null);

        int btnX = 0, btnY = 0;
        btns = Collections.synchronizedMap(new HashMap<Integer, Button>());
        for (int cnt = 1; cnt <= numOfFloor; cnt++) {
            Button button = new Button(cnt + "");
            button.setBounds((BTN_SIZE + 1) * btnX + 1, (BTN_SIZE + 1) * btnY + 1, BTN_SIZE, BTN_SIZE);
            button.addActionListener(this);
            btns.put(cnt, button);
            add(button);
            btnX++;
            if (cnt % maxWidthCnt == 0) {
                btnY++;
                btnX = 0;
            }
        }
    }

    public void toggleBtns(List<Integer> floors) {
        ArrayList<Integer> selectionFloor = new ArrayList<Integer>();
        for (Integer floor : floors) {
            selectionFloor.add(floor);
        }
        if(!selectionFloor.isEmpty()){
            Collections.sort(selectionFloor);
        }
        int cnt = 0;
        for (int i = 1; i <= btns.size(); i++) {
            Button button = btns.get(i);
            if (!selectionFloor.isEmpty()) {
                if (selectionFloor.size() > cnt && selectionFloor.get(cnt) == i) {
                    cnt++;
                    button.setBackground(Color.RED);
                } else {
                    button.setBackground(Color.WHITE);
                }
            } else {
                button.setBackground(Color.WHITE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int targetFloor = Integer.parseInt(command);
        if (inputBuffer.selectFloorInElevator(targetFloor)) {
            Button button = btns.get(targetFloor);
            button.setBackground(Color.RED);
        }

    }
}

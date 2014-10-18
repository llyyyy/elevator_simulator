package gui;

import java.awt.*;

/**
* Created by ksh on 2014-05-24.
*/
class BuildingPanel extends Panel {
    private int numOfFloor;
    private String ID;
    int x, y;
    public final int WIDTH = 50;
    private final int FLOOR_HEIGHT = 57;
    private int height = FLOOR_HEIGHT * numOfFloor;
    private ElevatorPanel elevatorPanel;

    public int getHeight(){
        return height;
    }

    public BuildingPanel(String ID, int numOfFloor, int x, int y) {
        this.ID = ID;
        this.numOfFloor = numOfFloor;
        this.x = x;
        this.y = y;
        height = numOfFloor * FLOOR_HEIGHT;
        elevatorPanel = new ElevatorPanel(y,height);
        add(elevatorPanel);

        setBounds(x, y, WIDTH, height);
        setLayout(null);


    }

    private Image image;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(image == null) {
            image = createImage(WIDTH, height);
            Graphics imageG = image.getGraphics();
            imageG.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

            for (int i = 1; i < numOfFloor; i++) {
                imageG.drawLine(0, FLOOR_HEIGHT * i, WIDTH, FLOOR_HEIGHT * i);
            }
        }else{
            g.drawImage(image,0,0,this);
        }
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }
}

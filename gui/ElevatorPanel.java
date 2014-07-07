package gui;

import middle.Elevator;
import middle.InputBuffer;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ksh on 2014-05-24.
 */
class ElevatorPanel extends Panel {
    public final int WIDTH = 46;
    public final int HEIGHT = 53;
    private int maxHeight;
    private int x, y;
    private int doorGap;
    private Elevator elevator;
    private InputBuffer inputBuffer;
    private int speed;
    private int doorSpeed=4;
    private int middleX;

    public ElevatorPanel( int y, int maxHeight) {
        this.x = 2;
        this.y = y + 2;
        this.maxHeight = maxHeight;
        setBackground(Color.GRAY);
        setBounds(x, y, WIDTH, HEIGHT);
        setLayout(null);

        inputBuffer = InputBuffer.getInstance();

        elevator = Elevator.getInstance();
        speed = elevator.SPEED;
        Timer timer = new Timer();
        Move move = new Move();
        timer.schedule(move, 0, 34);

        middleX = (x+WIDTH)/2;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Image image = createImage(getWidth(),getHeight());
        Graphics imageG = image.getGraphics();

        imageG.setColor(Color.BLACK);

        imageG.drawLine(middleX, 0, middleX, HEIGHT);

        imageG.setColor(Color.WHITE);
        imageG.fillRect(middleX -doorGap/2,0,doorGap,HEIGHT);
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }

    public boolean isDoorClose(){
        if(doorGap == 0){
            return true;
        }
        return false;
    }
    public void closeDoor(){
        doorGap =0;
    }

    class Move extends TimerTask {
        private void move() {
            Elevator.ServiceState serviceState = elevator.getServiceState();
            Elevator.MoveState moveState = elevator.getMoveState();
            if(isDoorClose() && serviceState == Elevator.ServiceState.SERVICE){
                if (moveState == Elevator.MoveState.UP) {
                    y -= speed;
                } else if (moveState == Elevator.MoveState.DOWN) {
                    y += speed;
                }
            }else if(serviceState == Elevator.ServiceState.PAUSE){
                Elevator.DoorState doorState = elevator.getCurrentDoorState();
                if(doorState == Elevator.DoorState.OPENING){
                    doorGap+=doorSpeed;
                }else if(doorState == Elevator.DoorState.CLOSEING){
                    doorGap-=doorSpeed;
                }else if(doorState == Elevator.DoorState.CLOSE){
                    closeDoor();
                }
            }else {
                closeDoor();
            }
            repaint();
        }
        private void checkServiceState(){
            Elevator.ServiceState serviceState = elevator.getServiceState();
            if(serviceState == Elevator.ServiceState.PAUSE) {
                elevator.setMoveState(Elevator.MoveState.NO_MOVE);
                elevator.setServiceState(Elevator.ServiceState.PAUSE);
                int currentFloor = elevator.getCurrentFloor();
                inputBuffer.removeSelectionFloorInElevator(currentFloor);
                inputBuffer.removeSelectionFloor(currentFloor);
            }
        }

        private void checkCurrentFloor() {
            if (y % (HEIGHT + 4) < 5) {
                int currentFloor = maxHeight / (HEIGHT + 4) - y / (HEIGHT + 4);
                elevator.setCurrentFloor(currentFloor);
            }
        }

        @Override
        public void run() {
            move();
            checkCurrentFloor();
            checkServiceState();
            setLocation(x, y);
        }
    }
}
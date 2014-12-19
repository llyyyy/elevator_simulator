package middle;

import java.util.*;

/**
 * Created by ksh on 2014-05-24.
 */
/*
엘레베이터의 상태를 저장하는 클래스
 */
public class Elevator {
    private static Elevator instance = null;
    public final int SPEED = 4;

    public DoorState getCurrentDoorState() {
        return currentDoorState;
    }


    public enum DoorState {OPEN, OPENING, CLOSE, CLOSEING}

    private DoorState currentDoorState = DoorState.CLOSE;

    public enum ServiceState {SERVICE, PAUSE, STOP}

    private ServiceState currentServiceState = ServiceState.PAUSE;

    public enum MoveState {UP, DOWN, NO_MOVE}

    private MoveState currentMoveState = MoveState.NO_MOVE;
    private List<Integer> targetFloors = Collections.synchronizedList(new ArrayList<Integer>());
    private int currentFloor = 8;


    private Elevator() {
        Timer timer = new Timer();
        CheckState checkState = new CheckState();
        timer.schedule(checkState, 0, 34);
    }

    public MoveState getMoveState() {
        return currentMoveState;
    }

    public ServiceState getServiceState() {
        return currentServiceState;
    }

    public void setServiceState(ServiceState currentServiceState) {
        this.currentServiceState = currentServiceState;
    }

    public void setMoveState(MoveState moveState) {
        currentMoveState = moveState;
    }


    public static Elevator getInstance() {
        if (instance == null) {
            synchronized (InputBuffer.class) {
                if (instance == null) {
                    instance = new Elevator();
                }
            }
        }
        return instance;
    }

    public void setCurrentFloor(int floor) {
        currentFloor = floor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setTargetFloors(ArrayList<Integer> targetFloors) {
        if (targetFloors != null) {
            if(!targetFloors.isEmpty()){
                this.targetFloors = targetFloors;
            }
        }
    }

    class CheckState extends TimerTask {
        private void changeState() {
            if (!targetFloors.isEmpty()) {
                currentServiceState = ServiceState.SERVICE;
                if(currentMoveState == MoveState.NO_MOVE){
                    //가까운 ?????
                   // currentMoveState = MoveState.UP;
                }
                if (selectDownFloor()) { currentMoveState = MoveState.UP; }
                if (selectUpFloor()) { currentMoveState = MoveState.DOWN; }
                if (selectHereFloor()){
                    if (currentServiceState != ServiceState.PAUSE) {
                        currentServiceState = ServiceState.PAUSE;
                        //currentMoveState = MoveState.NO_MOVE;

                        targetFloors.remove(0);
                        try {
                            openTheDoor();
                            closeTheDoor();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        currentServiceState = ServiceState.SERVICE;
                    }
                    if(currentFloor == 8 || currentFloor == 1){
                        currentMoveState = MoveState.NO_MOVE;
                    }
                }
            }
            else {
                stopElevator();
            }
        }

        private void stopElevator() {
            currentMoveState = MoveState.NO_MOVE;
            currentServiceState = ServiceState.PAUSE;
            currentDoorState = DoorState.CLOSE;
        }

        private void closeTheDoor() throws InterruptedException {
            int interval = 300;
            currentDoorState = DoorState.CLOSEING;
            Thread.sleep(interval);
            currentDoorState = DoorState.CLOSE;
        }

        private void openTheDoor() throws InterruptedException {
            int interval = 300;
            currentDoorState = DoorState.OPENING;
            Thread.sleep(interval);
            currentDoorState = DoorState.OPEN;
            Thread.sleep(interval);
        }

        private boolean selectHereFloor() {
            return targetFloors.get(0) == currentFloor;
        }

        private boolean selectUpFloor() {
            return targetFloors.get(0) < currentFloor;
        }

        private boolean selectDownFloor() {
            return targetFloors.get(0) > currentFloor;
        }

        @Override
        public void run() {
            changeState();
        }
    }
}

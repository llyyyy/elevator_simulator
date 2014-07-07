package configuration;

/**
 * Created by ksh on 2014-05-24.
 */
public class Configuration {
    private int numberOfElevators;
    private int numberOfFloors;
    private int speedOfElevator;
    private int waitTime;

    public int getNumberOfElevators() {
        return numberOfElevators;
    }

    public void setNumberOfElevators(int numberOfElevators) {
        this.numberOfElevators = numberOfElevators;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public int getSpeedOfElevator() {
        return speedOfElevator;
    }

    public void setSpeedOfElevator(int speedOfElevator) {
        this.speedOfElevator = speedOfElevator;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }
}

package pl.edu.pja.s27619.gui_pro3.service;

public class DataCollector {
    private int time;
    private int speed;
    private int error;
    public DataCollector(int time, int speed, int error) {
        this.time = time;
        this.speed = speed;
        this.error = error;

    }

    public int getTime() {
        return time;
    }

    public int getSpeed() {
        return speed;
    }

    public int getError() {
        return error;
    }
}

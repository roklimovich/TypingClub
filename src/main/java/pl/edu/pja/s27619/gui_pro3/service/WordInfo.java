package pl.edu.pja.s27619.gui_pro3.service;

import java.time.LocalTime;

public class WordInfo {

    private String word;
    private LocalTime startTime;
    private LocalTime endTime;

    public WordInfo(String word, LocalTime startTime, LocalTime endTime) {
        this.word = word;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getWord() {
        return word;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}

package com.professionalbeginner.presentation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Florian on 13/06/16.
 */
public class Date {

    LocalDate currentTime = LocalDate.MIN;
    String currentDate = "";

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    private void updateCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            currentTime = LocalDate.parse(currentDate, formatter);
        } catch (Exception e) {

        }
    }

    public LocalDate getCurrentTime() {
        updateCurrentTime();
        return currentTime;
    }
}

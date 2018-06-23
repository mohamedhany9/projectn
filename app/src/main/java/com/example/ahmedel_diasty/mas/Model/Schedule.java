package com.example.ahmedel_diasty.mas.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Schedule {

    @SerializedName("data")
    private ArrayList<DataSchedule> dataSchedules ;

    public Schedule(ArrayList<DataSchedule> dataSchedules) {
        this.dataSchedules = dataSchedules;
    }

    public Schedule() {
    }

    public ArrayList<DataSchedule> getDataSchedules() {
        return dataSchedules;
    }

    public void setDataSchedules(ArrayList<DataSchedule> dataSchedules) {
        this.dataSchedules = dataSchedules;
    }
}

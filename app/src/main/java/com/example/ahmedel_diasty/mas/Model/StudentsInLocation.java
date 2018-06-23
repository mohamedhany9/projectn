package com.example.ahmedel_diasty.mas.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StudentsInLocation {
    @SerializedName("data")
    private ArrayList<StudentsInLocationData> data ;

    public StudentsInLocation(ArrayList<StudentsInLocationData> data) {
        this.data = data;
    }

    public StudentsInLocation() {
    }

    public ArrayList<StudentsInLocationData> getData() {
        return data;
    }

    public void setData(ArrayList<StudentsInLocationData> data) {
        this.data = data;
    }
}

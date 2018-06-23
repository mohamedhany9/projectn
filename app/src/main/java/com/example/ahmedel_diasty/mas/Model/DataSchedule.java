package com.example.ahmedel_diasty.mas.Model;

import com.google.gson.annotations.SerializedName;

public class DataSchedule {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public DataSchedule(int id, String day, String location, String instructorName, String subjectName, String type, String groupNumber, String startTime, String endTime , String totalMark) {
        this.id = id;
        this.day = day;
        this.Location = location;
        this.instructorName = instructorName;
        this.subjectName = subjectName;
        this.type = type;
        this.groupNumber = groupNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalMark = totalMark;
    }

    @SerializedName("id")

    private int id;
    @SerializedName("day")
    private String day;
    @SerializedName("Location")
    private String  Location;
    @SerializedName("instructorName")
    private String instructorName;
    @SerializedName("subjectName")
    private String subjectName;
    @SerializedName("type")
    private String type;
    @SerializedName("groupNumber")
    private String groupNumber;
    @SerializedName("startTime")
    private String  startTime;
    @SerializedName("endTime")
    private String  endTime;



    public String getTotalMark() {

        return totalMark;
    }

    public void setTotalMark(String totalMark) {
        this.totalMark = totalMark;
    }

    @SerializedName("totalMark")
    private String  totalMark;
}

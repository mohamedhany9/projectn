package com.example.ahmedel_diasty.mas.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataQuestion {
    @SerializedName("data")
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> musics) {
        this.questions = questions;
    }


}
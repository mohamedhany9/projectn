package com.example.ahmedel_diasty.mas.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("question")
    @Expose
    private String question;

    @SerializedName("id")
    @Expose
    private int question_id;

    public Question(String question) {
        this.question = question;
    }

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", question_id=" + question_id +
                '}';
    }
}

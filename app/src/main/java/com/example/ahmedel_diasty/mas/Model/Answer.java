package com.example.ahmedel_diasty.mas.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answer {
    @SerializedName("student_id")
    @Expose
    int student_id ;
    @SerializedName("answer1")
    @Expose
    String student_answer_1 ;
    @SerializedName("answer2")
    @Expose
    String student_answer_2 ;
    @SerializedName("answer3")
    @Expose
    String student_answer_3 ;
    @SerializedName("answer4")
    @Expose
    String student_answer_4 ;




    public Answer( String student_answer_1, String student_answer_2, String student_answer_3, String student_answer_4 ) {

        this.student_answer_1 = student_answer_1;
        this.student_answer_2 = student_answer_2;
        this.student_answer_3 = student_answer_3;
        this.student_answer_4 = student_answer_4;

    }

    public Answer() {

    }


    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getStudent_answer_1() {
        return student_answer_1;
    }

    public void setStudent_answer_1(String student_answer_1) {
        this.student_answer_1 = student_answer_1;
    }

    public String getStudent_answer_2() {
        return student_answer_2;
    }

    public void setStudent_answer_2(String student_answer_2) {
        this.student_answer_2 = student_answer_2;
    }

    public String getStudent_answer_3() {
        return student_answer_3;
    }

    public void setStudent_answer_3(String student_answer_3) {
        this.student_answer_3 = student_answer_3;
    }

    public String getStudent_answer_4() {
        return student_answer_4;
    }

    public void setStudent_answer_4(String student_answer_4) {
        this.student_answer_4 = student_answer_4;
    }


    public Answer(String student_answer_1) {
        this.student_answer_1 = student_answer_1;
    }
}

package com.example.ahmedel_diasty.mas.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Ahmed El-Diasty on 05/03/2018.
 */

public class Model {
    @SerializedName("data")
    private ArrayList<Data> studentLogin ;




    public Model() {
    }

    public ArrayList<Data> getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(ArrayList<Data> studentLogin) {
        this.studentLogin = studentLogin;
    }


}

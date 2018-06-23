package com.example.ahmedel_diasty.mas.Remote;

import com.example.ahmedel_diasty.mas.Model.Model;
import com.example.ahmedel_diasty.mas.Model.Schedule;
import com.example.ahmedel_diasty.mas.Model.StudentsInLocation;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("api/students")
    Call<Model> getData();

    @FormUrlEncoded
    @POST("api/studentLogin")
    Call<Model> setData(@Field("username")String Username,
                        @Field("password")String Password);

    @FormUrlEncoded
    @POST("api/instructorLogin")
    Call<Model> setInstructorDta(@Field("username")String Username,
                        @Field("password")String Password);

    @GET("api/schedule")
    Call<Schedule>getScheduleCall();

    @GET("api/students_in_Location")
    Call<StudentsInLocation>getStudentsCall();


    @FormUrlEncoded
    @PUT("api/students_in_Location/{id}")
    Call<StudentsInLocation>setStudentsCall(@Path("id")int ID,
                                            @Field("status")String status);

//    @FormUrlEncoded
//    @GET("api/students_in_Location/{id}")
//    Call<StudentsInLocation>getStudentsCall(@Path("id")int ID);

}

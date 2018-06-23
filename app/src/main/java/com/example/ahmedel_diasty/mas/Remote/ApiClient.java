package com.example.ahmedel_diasty.mas.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String Base_Url = "http://syntax-eg.esy.es/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if(retrofit == null){
        retrofit = new Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}

package com.app.servit.api;

import com.app.servit.utils.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static RetrofitAPI instance = null;

    public static RetrofitAPI getInstance(){
        if(instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl(Utils.getUrlBase())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitAPI.class);
        }

        return instance;
    }
}

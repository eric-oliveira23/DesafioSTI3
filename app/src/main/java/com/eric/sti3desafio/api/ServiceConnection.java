package com.eric.sti3desafio.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceConnection {

    public Retrofit getBaseUrl(){
        return new Retrofit.Builder()
        .baseUrl("https://desafiotecnicosti3.azurewebsites.net")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    }
}

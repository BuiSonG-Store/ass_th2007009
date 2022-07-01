package com.example.ass_th2007009.network;

import com.example.ass_th2007009.model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {

    public static String BASE_URL = "http://dataservice.accuweather.com";

    @GET("/forecasts/v1/hourly/12hour/353412?apikey=iNGukiWlWOJBuAQE1xCncoLzMemQGquM&language=vi-vn&metric=true")
    Call<List<Weather>> gethour();

    @GET("/forecasts/v1/daily/5day/353412?apikey=iNGukiWlWOJBuAQE1xCncoLzMemQGquM&language=vi-vn&metric=true\n")
    Call<List<Weather>> getDay();
}

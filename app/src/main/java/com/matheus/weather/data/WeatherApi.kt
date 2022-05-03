package com.matheus.weather.data

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?&&")
    suspend fun getWeatherByLatLong(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String
    ): WeatherData
}
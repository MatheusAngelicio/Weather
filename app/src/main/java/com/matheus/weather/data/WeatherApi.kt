package com.matheus.weather.data

import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi {

    @GET("weather?lat={lat}&lon={lon}&appid=7ef91aaec275e174db09691ff32a1860")
    fun getWeatherByLatLong(@Path("lat") lat : String,
    @Path("lon") lon : String): WeatherData
}
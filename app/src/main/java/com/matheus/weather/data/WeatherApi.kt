package com.matheus.weather.data

import retrofit2.http.GET

interface WeatherApi {

    @GET("weather?lat=35&lon=139&appid=7ef91aaec275e174db09691ff32a1860")
    suspend fun getWeatherByLatLong(): WeatherData
}
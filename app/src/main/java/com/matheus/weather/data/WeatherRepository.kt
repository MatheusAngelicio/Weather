package com.matheus.weather.data

import com.matheus.weather.util.Resource

interface WeatherRepository {

    suspend fun getWeatherByLatLong(lat: String, lon: String, key: String): Resource<WeatherData>

}
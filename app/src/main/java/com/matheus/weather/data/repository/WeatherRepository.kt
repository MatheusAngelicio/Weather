package com.matheus.weather.data.repository

import com.matheus.weather.data.WeatherData
import com.matheus.weather.util.Resource

interface WeatherRepository {

    suspend fun getWeatherByLatLong(lat: String, lon: String, key: String): Resource<WeatherData>

}
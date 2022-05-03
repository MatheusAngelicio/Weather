package com.matheus.weather.data

import com.matheus.weather.util.Resource
import java.lang.Exception

class WeatherRepository(private val api: WeatherApi) {

    suspend fun getWeatherByLatLong(lat: String, lon: String, key: String): Resource<WeatherData> {
        val response = try {
            api.getWeatherByLatLong(lat, lon, key)
        } catch (e: Exception) {
            return Resource.Error("An unknow error occured : $e")
        }
        return Resource.Success(response)
    }
}
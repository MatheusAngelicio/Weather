package com.matheus.weather.data

import com.matheus.weather.util.Resource
import java.lang.Exception

class WeatherRepository(private val api: WeatherApi) {

    suspend fun getWeatherByLatLong(): Resource<WeatherData>{
        val response = try {
            api.getWeatherByLatLong()
        } catch (e: Exception) {
            val x = e
            val t = e
            return Resource.Error("An unknow error occured : $e")
        }
        return Resource.Success(response)
    }
}
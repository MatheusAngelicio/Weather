package com.matheus.weather.data.repository

import com.matheus.weather.data.WeatherApi
import com.matheus.weather.data.WeatherData
import com.matheus.weather.util.Resource

class WeatherRepositoryImpl(private val api: WeatherApi): WeatherRepository {
    override suspend fun getWeatherByLatLong(lat: String, lon: String, key: String
    ): Resource<WeatherData> {
        val response = try {
            api.getWeatherByLatLong(lat, lon, key)
        } catch (e: Exception) {
            return Resource.Error("An unknow error occured : $e")
        }
        return Resource.Success(response)
    }
}
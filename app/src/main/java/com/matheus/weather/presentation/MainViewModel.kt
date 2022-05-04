package com.matheus.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.weather.data.WeatherData
import com.matheus.weather.data.WeatherRepository
import com.matheus.weather.util.API_KEY
import com.matheus.weather.util.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weather = MutableLiveData<WeatherData?>()
    val weather: LiveData<WeatherData?> = _weather

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    val isLoading = MutableLiveData(false)

    companion object {
        private val mockLat = "-25.438124871326547"
        private val mockLon = "-49.27197230420212"
    }


    init {
        loadWeather()
    }

    fun loadWeather() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getWeatherByLatLong(mockLat, mockLon, API_KEY)
            when (result) {
                is Resource.Success -> {
                    _weather.value = result.data
                    isLoading.value = false
                    _error.value = false
                }
                is Resource.Error -> {
                    _error.value = true
                    isLoading.value = false
                }
            }
        }


    }
}
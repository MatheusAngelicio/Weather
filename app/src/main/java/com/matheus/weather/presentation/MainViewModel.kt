package com.matheus.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.weather.data.WeatherData
import com.matheus.weather.data.WeatherRepository
import com.matheus.weather.util.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weather = MutableLiveData<WeatherData?>()
    val weather: LiveData<WeatherData?> = _weather

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error


    init {
        loadWeather()
    }

    fun loadWeather() {
        viewModelScope.launch {
            val result = repository.getWeatherByLatLong()
            when (result) {
                is Resource.Success -> {
                    _weather.value = result.data
                    _error.value = false
                }
                is Resource.Error -> {
                    _error.value = true
                }
            }
        }


    }
}
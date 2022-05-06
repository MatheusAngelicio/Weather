package com.matheus.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.weather.data.WeatherData
import com.matheus.weather.data.repository.WeatherRepository
import com.matheus.weather.util.API_KEY
import com.matheus.weather.util.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weather = MutableLiveData<WeatherData?>()
    val weather: LiveData<WeatherData?> = _weather

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    val isLoading = MutableLiveData(false)

    private var lat: String? = null
    private var lon: String? = null

    fun setupLatLon(lat: Double, lon: Double) {
        this.lat = lat.toString()
        this.lon = lon.toString()
        loadWeather()
    }

    fun loadWeather() {
        viewModelScope.launch {
            isLoading.value = true
            if (!lat.isNullOrEmpty() && !lon.isNullOrEmpty()){
                val result = repository.getWeatherByLatLong(lat!!, lon!!, API_KEY)
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
}
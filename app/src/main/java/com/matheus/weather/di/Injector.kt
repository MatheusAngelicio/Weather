package com.matheus.weather.di

import com.matheus.weather.WebServiceClient
import com.matheus.weather.data.WeatherRepository
import com.matheus.weather.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    single { WebServiceClient().weatherApi}
    single { WeatherRepository(get()) }

    viewModel { MainViewModel(get()) }


}
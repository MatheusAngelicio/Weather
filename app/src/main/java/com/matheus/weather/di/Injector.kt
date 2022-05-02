package com.matheus.weather.di

import com.matheus.weather.WebServiceClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    single { WebServiceClient().weatherApi}

}
package com.matheus.weather

import com.matheus.weather.data.WeatherApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


class WebServiceClient {

    var weatherApi: WeatherApi

    init {
        weatherApi =
            createRetrofitAccess("https://api.openweathermap.org/data/2.5/")
    }

    private fun createRetrofitAccess(
        url: String
    ): WeatherApi {
        val kotlinJsonAdapter = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder().baseUrl(url).client(OkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(kotlinJsonAdapter)).build()
            .create(WeatherApi::class.java)

    }


}
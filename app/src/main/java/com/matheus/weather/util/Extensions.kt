package com.matheus.weather.util

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun formatSunriseSunset(item: Long?): String {
    item?.let {
        return SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(it * 1000))
    }
    return "---"
}

fun convertFahrenheitToCelcius(temperature: Double?): String{
    temperature?.let {
        val tempFormated = (temperature - 32) * 5/9
        return tempFormated.toString()
    }
    return "---"
}
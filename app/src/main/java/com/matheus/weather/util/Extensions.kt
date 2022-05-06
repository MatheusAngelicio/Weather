package com.matheus.weather.util

import android.content.Context
import android.view.View
import com.matheus.weather.R
import java.text.SimpleDateFormat
import java.util.*

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun formatNumberToHourMinute(item: Long?): String {
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

fun formatUpdateAtText(dt: Long?, context: Context): String {
    dt?.let {
        return context.getString(
            R.string.update_at,
            SimpleDateFormat(
                "dd/MM/yyyy hh:mm a",
                Locale.ENGLISH
            ).format(Date(dt * 1000))
        )
    }
    return "---"
}
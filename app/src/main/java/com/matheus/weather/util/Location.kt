package com.matheus.weather.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices

class Location(private val context: Context) {

    private val locationProvider = LocationServices.getFusedLocationProviderClient(context)

    var onAddressListener: ((item: Pair<Double, Double>) -> Unit)? = null

    fun fetchLocation() {
        val task = locationProvider.lastLocation

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(context as Activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
        }
        task.addOnSuccessListener {address ->
            if (address != null) {
                val latLon = Pair(address.latitude, address.longitude)
                onAddressListener?.invoke(latLon)
            }
        }
    }
}
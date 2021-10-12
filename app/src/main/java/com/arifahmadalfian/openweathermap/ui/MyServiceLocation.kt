package com.arifahmadalfian.openweathermap.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.arifahmadalfian.openweathermap.ui.fragments.HomeFragment
import com.google.android.gms.location.LocationResult

class MyServiceLocation : BroadcastReceiver() {

    companion object {
        const val ACTION_PROCESS_UPDATE = "com.arifahmadalfian.openweathermap.ui.UPDATE_LOCATION"
    }
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1 != null) {
            val action: String = p1.action.toString()
            if (ACTION_PROCESS_UPDATE == action) {
                val result = LocationResult.extractResult(p1)
                val location = result.lastLocation
                val lat = location.latitude
                val lng = location.longitude
                try {

                } catch (e: Exception) {

                }
            }
        }
    }

}
package com.arifahmadalfian.openweathermap.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherItem(

    @field:SerializedName("icon")
    val icon: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("main")
    val main: String,

    @field:SerializedName("id")
    val id: Int
): Parcelable
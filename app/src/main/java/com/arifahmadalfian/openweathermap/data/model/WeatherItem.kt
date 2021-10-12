package com.arifahmadalfian.openweathermap.data.model

import com.google.gson.annotations.SerializedName

data class WeatherItem(

    @field:SerializedName("icon")
    val icon: String? = null,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("main")
    val main: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)
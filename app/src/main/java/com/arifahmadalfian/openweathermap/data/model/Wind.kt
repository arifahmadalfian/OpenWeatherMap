package com.arifahmadalfian.openweathermap.data.model

import com.google.gson.annotations.SerializedName

data class Wind(

    @field:SerializedName("deg")
    val deg: Int,

    @field:SerializedName("speed")
    val speed: Double,

    @field:SerializedName("gust")
    val gust: Double
)
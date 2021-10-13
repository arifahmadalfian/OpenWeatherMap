package com.arifahmadalfian.openweathermap.data.model

import com.google.gson.annotations.SerializedName


data class Sys(

    @field:SerializedName("country")
    val country: String,

    @field:SerializedName("sunrise")
    val sunrise: Int,

    @field:SerializedName("sunset")
    val sunset: Int
)

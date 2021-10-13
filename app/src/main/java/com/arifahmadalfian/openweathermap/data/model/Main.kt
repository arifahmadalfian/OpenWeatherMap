package com.arifahmadalfian.openweathermap.data.model

import com.google.gson.annotations.SerializedName

data class Main(

    @field:SerializedName("temp")
    val temp: Double,

    @field:SerializedName("temp_min")
    val tempMin: Double,

    @field:SerializedName("grnd_level")
    val grndLevel: Int,

    @field:SerializedName("humidity")
    val humidity: Int,

    @field:SerializedName("pressure")
    val pressure: Int,

    @field:SerializedName("sea_level")
    val seaLevel: Int,

    @field:SerializedName("feels_like")
    val feelsLike: Double,

    @field:SerializedName("temp_max")
    val tempMax: Double
)

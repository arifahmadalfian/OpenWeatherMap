package com.arifahmadalfian.openweathermap.data.model

import com.google.gson.annotations.SerializedName

data class Clouds(

    @field:SerializedName("all")
    val all: Int
)
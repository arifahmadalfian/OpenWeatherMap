package com.arifahmadalfian.openweathermap.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wind(

    @field:SerializedName("deg")
    val deg: Int,

    @field:SerializedName("speed")
    val speed: Double,

    @field:SerializedName("gust")
    val gust: Double
): Parcelable
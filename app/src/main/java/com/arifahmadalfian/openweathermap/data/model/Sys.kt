package com.arifahmadalfian.openweathermap.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sys(

    @field:SerializedName("country")
    val country: String,

    @field:SerializedName("sunrise")
    val sunrise: Int,

    @field:SerializedName("sunset")
    val sunset: Int
): Parcelable

package com.arifahmadalfian.openweathermap.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Clouds(

    @field:SerializedName("all")
    val all: Int
): Parcelable
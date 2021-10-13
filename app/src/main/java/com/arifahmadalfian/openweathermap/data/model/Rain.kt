package com.arifahmadalfian.openweathermap.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rain(

    @field:SerializedName("3h")
    val jsonMember3h: Double
): Parcelable
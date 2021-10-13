package com.arifahmadalfian.openweathermap.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(

	@field:SerializedName("country")
	val country: String,

	@field:SerializedName("coord")
	val coord: Coord,

	@field:SerializedName("sunrise")
	val sunrise: Int,

	@field:SerializedName("timezone")
	val timezone: Int,

	@field:SerializedName("sunset")
	val sunset: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("population")
	val population: Int
): Parcelable


package com.arifahmadalfian.openweathermap.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BaseResponse(

	@field:SerializedName("city")
	val city: City,

	@field:SerializedName("cnt")
	val cnt: Int,

	@field:SerializedName("cod")
	val cod: String,

	@field:SerializedName("message")
	val message: Int,

	@field:SerializedName("list")
	val list: List<ListItem>
): Parcelable





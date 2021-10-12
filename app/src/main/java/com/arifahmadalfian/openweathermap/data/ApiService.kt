package com.arifahmadalfian.openweathermap.data

import com.arifahmadalfian.openweathermap.data.model.BaseResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //bandung - 9548adeb8142fcc0cb867b2aa6c826d3

    @GET("forecast")
    suspend fun getWeatherWeek(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units")units: String = "metric",
        @Query("appid") appId: String = "9548adeb8142fcc0cb867b2aa6c826d3"
    ): Response<BaseResponse>
}
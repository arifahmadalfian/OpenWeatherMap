package com.arifahmadalfian.openweathermap.data

import com.arifahmadalfian.openweathermap.data.model.BaseResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast")
    suspend fun getWeatherWeek(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units")units: String = "metric",
        @Query("appid") appId: String = "61916f7fdd2d489a911431c1e5a1a584"
    ): Response<BaseResponse>
}
package com.arifahmadalfian.openweathermap.data

import com.arifahmadalfian.openweathermap.data.model.BaseResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(private val apiService: ApiService) {

   suspend fun getWeatherWeek(lat: Double, lon: Double): Response<BaseResponse> =
      apiService.getWeatherWeek(lat = lat, lon = lon)

}
package com.arifahmadalfian.openweathermap.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifahmadalfian.openweathermap.data.Repository
import com.arifahmadalfian.openweathermap.data.model.BaseResponse
import com.arifahmadalfian.openweathermap.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var _weatherWeek = MutableLiveData<ResponseState<BaseResponse>>(ResponseState.Empty)
    val weatherWeek: LiveData<ResponseState<BaseResponse>> get() =  _weatherWeek

    fun getWeatherWeek(
        lat: Double = -6.9039,
        lon: Double = 107.6186)
    {
        _weatherWeek.value = ResponseState.Loading
        try {
            viewModelScope.launch {
                val response = repository.getWeatherWeek(lat = lat, lon = lon)
                if (response.isSuccessful) {
                    _weatherWeek.value = ResponseState.Success(response.body()!!)
                }
            }
        } catch (e: Exception) {
            _weatherWeek.value = ResponseState.Error(e)
        }
    }
}


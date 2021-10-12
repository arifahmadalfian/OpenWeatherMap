package com.arifahmadalfian.openweathermap.data.di

import com.arifahmadalfian.openweathermap.data.ApiService
import com.arifahmadalfian.openweathermap.data.Constans.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @get:Singleton
    @get:Provides
    val okHttpClient: OkHttpClient
        get() {
            val okHttpClientBuilder = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
            return okHttpClientBuilder.build()
        }

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient?): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient!!)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(): ApiService {
        val retrofit = getRetrofit(okHttpClient)
        return retrofit.create(ApiService::class.java)
    }

}
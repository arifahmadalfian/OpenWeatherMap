package com.arifahmadalfian.openweathermap.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.arifahmadalfian.openweathermap.R
import java.text.SimpleDateFormat
import java.util.*

fun getDateTime(s: String): String? {
    return try {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val netDate = Date(s.toLong())
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}

fun getDay(s: String): String? {
    return try {
        val sdf = SimpleDateFormat("EEEE")
        val netDate = Date(s.toLong())
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}

suspend fun getBitmapUrl(context: Context, url: String?): Bitmap {
    val loading = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(url)
        .crossfade(true)
        .transformations(CircleCropTransformation())
        .scale(Scale.FILL)
        .build()
    val result = (loading.execute(request) as SuccessResult).drawable
    return (result as BitmapDrawable).bitmap
}

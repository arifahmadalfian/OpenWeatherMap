package com.arifahmadalfian.openweathermap.utils

import android.annotation.SuppressLint
import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * ctrl + Q
 * @param style when value is
 * 1 -> 2021-05-28 12:00
 * 2 -> Selasa, 17 Oktober 2021 12:00
 * 3 -> 12:00 17-Okt-2021
 * 4 -> 17/08/2021
 */
fun Long.epochToDateTime(style: Int): String {
    val date = Date(this * 1000L)
    val fmt: String = when (style) {
       1 -> "yyyy-MM-dd HH:mm" 
       2 -> "EEEE, d MMM yyyy HH:mm"
       3 -> "HH:mm dd-MM-yyyy" 
       4 -> "dd/MM/yyyy"
    }
    val sdf = SimpleDateFormat(fmt)
    return sdf.format(date)
}

@SuppressLint("SimpleDateFormat")
fun Long.epochToDay(): String {
    val date = Date(this * 1000L)
    val sdf = SimpleDateFormat("EEEE, d MMM yyyy HH:mm")
    return sdf.format(date)
}

fun getToday(): String {
    val date = Calendar.getInstance().time
    val hariIni = DateFormat.format("EEEE", date) as String
    val tanggal = DateFormat.format("d MMM yyyy", date) as String
    return "$hariIni, $tanggal"
}

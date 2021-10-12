package com.arifahmadalfian.openweathermap.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.arifahmadalfian.openweathermap.R
import com.arifahmadalfian.openweathermap.data.model.ListItem
import com.arifahmadalfian.openweathermap.databinding.RecyclerItemBinding
import com.arifahmadalfian.openweathermap.utils.getDay
import java.util.*

class AdapterMain(
    private val items: List<ListItem>
    ) : RecyclerView.Adapter<AdapterMain.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItem) {
            with(binding) {
                tvItesDays.text = getDay(item.dt.toString())
                Log.d("adapter", "${getDay(item.dt.toString())}")
                tvItemTempMin.text = String.format(Locale.getDefault(), "%.0f°C", item.main?.tempMin)
                tvItemTempMax.text = String.format(Locale.getDefault(), "%.0f°C", item.main?.tempMax)
                ivItemWeather.load("http://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png"){
                    placeholder(R.drawable.ic_baseline_image_24)
                    error(R.drawable.ic_baseline_broken_image_24)
                }

//                when (item.weather[0].description) {
//                    "broken clouds" -> {
//                        Glide.with(binding.ivItemWeather).load(R.drawable.ic_berawan)
//                    }
//                    "light rain" -> {
//                        Glide.with(binding.ivItemWeather).load(R.drawable.ic_hujan_gerimis)
//                    }
//                    "overcast clouds" -> {
//                        Glide.with(binding.ivItemWeather).load(R.drawable.ic_mendung)
//                    }
//                    "moderate rain" -> {
//                        Glide.with(binding.ivItemWeather).load(R.drawable.ic_hujan_lebat)
//                    }
//                    "few clouds" -> {
//                        Glide.with(binding.ivItemWeather).load(R.drawable.ic_mendung)
//                    }
//                    "heavy intensity rain" -> {
//                        Glide.with(binding.ivItemWeather).load(R.drawable.ic_hujan_petir).into(binding.ivItemWeather)
//                    }
//                    "clear sky" -> {
//                        Glide.with(binding.ivItemWeather).load(R.drawable.ic_panas)
//                    }
//                    "scattered clouds" -> {
//                        Glide.with(binding.ivItemWeather).load(R.drawable.ic_hujan_lebat)
//                    }
//                    else -> {
//                        Glide.with(binding.ivItemWeather).load(R.drawable.ic_hujan_lebat)
//                    }
//                }
            }
        }
    }
}
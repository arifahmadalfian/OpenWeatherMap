package com.arifahmadalfian.openweathermap.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.arifahmadalfian.openweathermap.R
import com.arifahmadalfian.openweathermap.data.model.ListItem
import com.arifahmadalfian.openweathermap.databinding.RecyclerItemBinding
import com.arifahmadalfian.openweathermap.ui.fragments.OnItemClick
import com.arifahmadalfian.openweathermap.utils.epochToDay
import java.util.*

class AdapterMain(
    private val items: List<ListItem>
    ) : RecyclerView.Adapter<AdapterMain.ViewHolder>() {

    private var onClickItem: OnItemClick? = null

    fun onItemClick(onClickItem: OnItemClick) {
        this.onClickItem = onClickItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onClickItem?.onItemClick(position)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItem) {
            with(binding) {
                tvItesDays.text = item.dt.toLong().epochToDay()
                tvItemTempMin.text = String.format(Locale.getDefault(), "%.0f°C", item.main.tempMin)
                tvItemTempMax.text = String.format(Locale.getDefault(), "%.0f°C", item.main.tempMax)
                ivItemWeather.load("http://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png"){
                    placeholder(R.drawable.ic_baseline_image_24)
                    error(R.drawable.ic_baseline_broken_image_24)
                }

            }
        }
    }
}
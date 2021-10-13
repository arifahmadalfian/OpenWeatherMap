package com.arifahmadalfian.openweathermap.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.arifahmadalfian.openweathermap.R
import com.arifahmadalfian.openweathermap.data.model.ListItem
import com.arifahmadalfian.openweathermap.databinding.FragmentDetailBinding
import com.arifahmadalfian.openweathermap.databinding.FragmentHomeBinding
import com.arifahmadalfian.openweathermap.utils.epochToDay
import com.arifahmadalfian.openweathermap.utils.getToday
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var listItem: ListItem? = null
    private var city = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            listItem = DetailFragmentArgs.fromBundle(it).weather
            city = DetailFragmentArgs.fromBundle(it).city
        }

        binding.ivWeather.load("http://openweathermap.org/img/wn/${listItem?.weather?.get(0)?.icon}@2x.png") {
            placeholder(R.drawable.ic_baseline_image_24)
            error(R.drawable.ic_baseline_broken_image_24)
        }
        binding.tvToday.text = listItem?.dt?.toLong()?.epochToDay()
        binding.tvTempMax.text = getString(R.string.tempMax, listItem?.main?.tempMax)
        binding.tvTempMin.text = getString(R.string.tempMin, listItem?.main?.tempMin)
        binding.tvStatusWeather.text = listItem?.weather?.get(0)?.main
        binding.tvPreasure.text = getString(R.string.preassure, listItem?.main?.pressure?.toString())
        binding.tvWind.text = getString(R.string.wind, listItem?.wind?.speed.toString())
        binding.tvLocation.text = getString(R.string.city, city)
        binding.tvHumidity.text = getString(R.string.humidity, "${listItem?.main?.humidity?.toString()}%")

        binding.ivBack.setOnClickListener {
            DetailFragmentDirections.actionDetailFragmentToHomeFragment().also {
                findNavController().navigate(it)
            }
        }

        binding.ivSend.setOnClickListener {
            val content = "$city pada tanggal ${listItem?.dt?.toLong()?.epochToDay()} bersuhu maksimal ${listItem?.main?.temp}"
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, content)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(intent, "Bagikan ke: "))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.arifahmadalfian.openweathermap.ui.fragments

import android.annotation.SuppressLint
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

    @SuppressLint("StringFormatInvalid", "StringFormatMatches")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            listItem = DetailFragmentArgs.fromBundle(it).weather
            city = DetailFragmentArgs.fromBundle(it).city
        }

        binding.tvToday.text = getToday()
        binding.tvTempMax.text =
            String.format(Locale.getDefault(), "%.0f°C", listItem?.main?.tempMax)
        binding.tvTempMin.text =
            String.format(Locale.getDefault(), "%.0f°C", listItem?.main?.tempMin)
        binding.tvStatusWeather.text = listItem?.weather?.get(0)?.main
        binding.ivWeather.load("http://openweathermap.org/img/wn/${listItem?.weather?.get(0)?.icon}@2x.png") {
            placeholder(R.drawable.ic_baseline_image_24)
            error(R.drawable.ic_baseline_broken_image_24)
        }
        binding.tvHumidity.text = resources.getString(R.string.humidity, listItem?.main?.humidity)
        binding.tvPreasure.text = resources.getString(R.string.preassure, listItem?.main?.pressure)
        binding.tvWind.text = resources.getString(R.string.wind, listItem?.wind?.speed)
        binding.tvLocation.text = resources.getString(R.string.city, city)

        binding.ivBack.setOnClickListener {
            DetailFragmentDirections.actionDetailFragmentToHomeFragment().also {
                findNavController().navigate(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.arifahmadalfian.openweathermap.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.arifahmadalfian.openweathermap.data.model.BaseResponse
import com.arifahmadalfian.openweathermap.R
import com.arifahmadalfian.openweathermap.data.model.ListItem
import com.arifahmadalfian.openweathermap.databinding.FragmentHomeBinding
import com.arifahmadalfian.openweathermap.ui.AdapterMain
import com.arifahmadalfian.openweathermap.ui.SharedViewModel
import com.arifahmadalfian.openweathermap.utils.ResponseState
import com.arifahmadalfian.openweathermap.utils.getToday
import com.google.android.gms.location.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SharedViewModel by viewModels()

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var adapterMain: AdapterMain
    private val listItem: MutableList<ListItem> = ArrayList()
    private var baseResponse: BaseResponse? = null

    var permissionArrays = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (ContextCompat.checkSelfPermission(requireContext()
                , Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(requireContext()
                , Manifest.permission.ACCESS_COARSE_LOCATION)
             == PackageManager.PERMISSION_GRANTED
        ) {
            getLocation()
        } else {
            requestPermissions(permissionArrays,100);
        }

        observeViewModel()

        adapterMain = AdapterMain(listItem)
        with(binding.rvList) {
            this.layoutManager = LinearLayoutManager(requireActivity())
            this.adapter = adapterMain
        }
        baseResponse?.let { initView(it) }
    }

    private fun getLocation() {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
        val locationManager: LocationManager =
            context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        ) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                val location = it.result
                if (location != null) {
                    viewModel.getWeatherWeek(lat = location.latitude, lon = location.longitude)
                    Log.d("listlocation", "${location.latitude} ${location.longitude}")
                } else {
                    val locationRequest = LocationRequest()
                    with(locationRequest) {
                        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                        interval = 10000
                        fastestInterval = 1000
                        setNumUpdates(1)
                    }
                    val locationCallback = object : LocationCallback() {
                        override fun onLocationResult(p0: LocationResult) {
                            val loc = p0.lastLocation
                            viewModel.getWeatherWeek(lat = loc.latitude, lon = loc.longitude)
                            Log.d("listloc", "${loc.latitude} ${loc.longitude}")
                        }
                    }
                    fusedLocationProviderClient.requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        Looper.myLooper()
                    )
                }
            }
        } else {
            startActivity(
                Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }


    @SuppressLint("SetTextI18n")
    private fun initView(data: BaseResponse) {
        binding.ivWeather.setOnClickListener {
            HomeFragmentDirections.actionHomeFragmentToDetailFragment().also {
                findNavController().navigate(it)
            }
        }

        binding.refreshLayout.setOnRefreshListener {
            getLocation()
        }

        binding.tvToday.text = getToday()
        binding.tvLocation.text = "${data.city.name}, ${data.city.country}"
        binding.tvTempMax.text =
            String.format(Locale.getDefault(), "%.0f°C", data.list[0].main.tempMax)
        binding.tvTempMin.text =
            String.format(Locale.getDefault(), "%.0f°C", data.list[0].main.tempMin)
        binding.tvStatusWeather.text = data.list[0].weather[0].main
        binding.ivWeather.load("http://openweathermap.org/img/wn/${data.list[0].weather[0].icon}@2x.png"){
            placeholder(R.drawable.ic_baseline_image_24)
            error(R.drawable.ic_baseline_broken_image_24)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeViewModel() {
        viewModel.weatherWeek.observe(viewLifecycleOwner, {
            when (it) {
                is ResponseState.Error -> {
                    Toast.makeText(requireContext(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                    binding.pbLoading.isVisible = false
                    binding.refreshLayout.isRefreshing = false
                }
                is ResponseState.Loading -> {
                    binding.pbLoading.isVisible = true
                }
                is ResponseState.Success -> {
                    listItem.clear()
                    binding.pbLoading.isVisible = false
                    binding.refreshLayout.isRefreshing = false
                    baseResponse = it.data
                    for (i in it.data.list.indices) {
                        listItem.add(it.data.list[i])
                    }
                    adapterMain.notifyDataSetChanged()
                    initView(it.data)
                }
                is ResponseState.Empty -> {
                }
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && (grantResults.isNotEmpty()) && (grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED)){
            getLocation()
        } else {
            Toast.makeText(requireContext(), "Izin Lokasi gagal", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


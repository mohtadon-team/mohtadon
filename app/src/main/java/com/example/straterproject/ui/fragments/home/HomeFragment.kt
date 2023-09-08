package com.example.straterproject.ui.fragments.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.GetTodayPrayerTimesUseCase
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentHomeBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.utilities.LATITUDE
import com.example.straterproject.utilities.LONGITUDE
import com.example.straterproject.utilities.SH_PER_FILE_NAME
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalTime
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(

) {
    override val layoutFragmentId: Int=R.layout.fragment_home
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val time = System.currentTimeMillis()
//        Log.i("ahmed", time.toString())

//        val current = LocalTime.now().isBefore(LocalTime()).toString()
//        Log.i("ahmed",  current)





    }


//    @SuppressLint("MissingPermission")
//    private fun getCurrentLocation(){
//        if (isLocationPermissionTaken()){
//            if (isLocationEnable()){
//                var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
//                lateinit var locationCallback: LocationCallback
//
//                var locationRequest: LocationRequest =
//                    LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY , TimeUnit.MINUTES.toMillis(5)).apply {
//                        setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
//                        setDurationMillis(TimeUnit.MINUTES.toMillis(5))
//                        setWaitForAccurateLocation(true)
//                        setMaxUpdates(1)
//                    }.build()
//
//                locationCallback = object : LocationCallback() {
//                    override fun onLocationResult(p0: LocationResult) {
//                        super.onLocationResult(p0)
//                        val currentLocation: Location? = p0.lastLocation
//                        currentLocation?.latitude
//
//                        currentLocation?.time
//                        val sharedPreferences: SharedPreferences = getSharedPreferences(Constants.SH_PER_FILE_NAME,
//                            Context.MODE_PRIVATE)
//                        val editor: SharedPreferences.Editor =  sharedPreferences.edit()
//
//                        editor.putString(Constants.LATATUIDE, currentLocation?.latitude.toString())
//                        editor.putString(Constants.LONGATUITE,  currentLocation?.longitude.toString())
//                        editor.apply()
//
//                    }
//                }
//                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
//
//            }else{
//                requestLocationEnable()
//            }
//        }else {
//            requestLocationPermission()
//            getCurrentLocation()
//        }
//    }
//
//
//    private fun isLocationPermissionTaken(): Boolean {
//        if (ActivityCompat.checkSelfPermission(this ,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED )
//            if (ActivityCompat.checkSelfPermission(this ,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                ) == PackageManager.PERMISSION_GRANTED)
//                return true
//        return false
//    }
//
//
//    private fun isLocationEnable(): Boolean {
//        val locationManager : LocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
//                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//    }
//
//
//
//    private fun requestLocationEnable() {
//        val gpsOptionsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//        startActivity(gpsOptionsIntent)
//    }
//
//
//    private fun requestLocationPermission() {
//        ActivityCompat.requestPermissions(this,
//            arrayOf(
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ), Constants.REQUEST_PERMISSION_CODE )
//    }
//
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if ( requestCode == Constants.REQUEST_PERMISSION_CODE &&
//            permissions.isNotEmpty() &&
//            grantResults[0] == PackageManager.PERMISSION_GRANTED &&
//            grantResults[1] == PackageManager.PERMISSION_GRANTED)
//        {
//            getCurrentLocation()
//        }
//        else {
//            requestLocationPermission()
//        }

//    }
}
package com.example.straterproject.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
 import com.example.straterproject.databinding.ActivityHomeBinding
import com.example.straterproject.ui.base.BaseActivity
import com.example.straterproject.ui.reciters.player.AudioItemPlayerViewModel
import com.example.straterproject.utilities.AzanPrayersUtil
import com.example.straterproject.utilities.LATITUDE
import com.example.straterproject.utilities.LONGITUDE
import com.example.straterproject.utilities.LastPlayedTrackPreference
import com.example.straterproject.utilities.REQUEST_PERMISSION_CODE
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override val layoutActivityId: Int = com.example.straterproject.R.layout.activity_home
    lateinit var navController: NavController

    @Inject
    lateinit var lastPlayedTrackPreference: LastPlayedTrackPreference

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment = supportFragmentManager.findFragmentById(com.example.straterproject.R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController

        lastPlayedTrackPreference.lastPlayedTrackId = -1
        lastPlayedTrackPreference.beforelastPlayedTrackId = -1
        // ar language
        val locale = Locale("ar")
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        // light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                com.example.straterproject.R.id.homeFragment -> {
                    navController.navigate(com.example.straterproject.R.id.homeFragment)
                    true
                }

                com.example.straterproject.R.id.recitersFragment -> {
                    navController.navigate(com.example.straterproject.R.id.recitersFragment)
                    true
                }

                com.example.straterproject.R.id.quranOptionsFragment -> {
                    navController.navigate(com.example.straterproject.R.id.quranOptionsFragment)
                    true
                }

                com.example.straterproject.R.id.fmRadioFragment -> {
                    navController.navigate(com.example.straterproject.R.id.fmRadioFragment)
                    true
                }

                com.example.straterproject.R.id.moreFragment -> {
                    navController.navigate(com.example.straterproject.R.id.moreFragment)
                    true
                }

                else -> false
            }
        }
        // set default selection to homeFragment
        binding.bottomNav.selectedItemId = com.example.straterproject.R.id.homeFragment
        getCurrentLocation()

        AzanPrayersUtil().registerPrayers(this)

    }


    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {

        if (isLocationPermissionTaken()) {
            if (isLocationEnable()) {

                val fusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(this)
                lateinit var locationCallback: LocationCallback

                val locationRequest: LocationRequest = LocationRequest.Builder(
                    Priority.PRIORITY_HIGH_ACCURACY, TimeUnit.MINUTES.toMillis(5)
                ).apply {
                    setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                    setDurationMillis(TimeUnit.MINUTES.toMillis(5))
                    setWaitForAccurateLocation(true)
                    setMaxUpdates(1)
                }.build()

                locationCallback = object : LocationCallback() {
                    override fun onLocationResult(p0: LocationResult) {
                        super.onLocationResult(p0)
                        val currentLocation: Location? = p0.lastLocation
                        currentLocation?.latitude

                        currentLocation?.time


                        editor = sharedPreferences.edit()
                        editor.putString(LATITUDE, currentLocation?.latitude.toString())
                        editor.putString(LONGITUDE, currentLocation?.longitude.toString())
                        editor.commit()


                    }
                }
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest, locationCallback, Looper.getMainLooper()
                )

            } else {
                requestLocationEnable()
            }
        } else {
            requestLocationPermission()

        }
    }


    private fun isLocationPermissionTaken(): Boolean {
        return (ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun isLocationEnable(): Boolean {
        val locationManager: LocationManager =
            this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }


    private fun requestLocationEnable() {
        val gpsOptionsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(gpsOptionsIntent)
    }


    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            ), REQUEST_PERMISSION_CODE
        )
    }


    override fun onRequestPermissionsResult(
      requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ){
          super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE && permissions.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] ==
            PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation()
        } else {
            requestLocationPermission()
        }
    }


}
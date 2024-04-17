package com.mohtdon.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.ActivityHomeBinding
import com.mohtdon.ui.base.BaseActivity
import com.mohtdon.utilities.AzanPrayersUtil
import com.mohtdon.utilities.LATITUDE
import com.mohtdon.utilities.LONGITUDE
import com.mohtdon.utilities.LastPlayedTrackPreference
import com.mohtdon.utilities.REQUEST_PERMISSION_CODE
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>()

{
    override val layoutActivityId: Int = R.layout.activity_home
    lateinit var navController: NavController

    @Inject
    lateinit var lastPlayedTrackPreference: LastPlayedTrackPreference

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController



        lastPlayedTrackPreference.lastPlayedTrackId = -1
        lastPlayedTrackPreference.beforelastPlayedTrackId = -1
        // ar language
        val locale = Locale("ar")
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupNavigation()
        binding.bottomNav.selectedItemId = R.id.homeFragment
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


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_PERMISSION_CODE && grantResults.isNotEmpty()) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                //  permissions granted proceed with the location
                getCurrentLocation()
            } else {
                //alert dialog to notify user that should enable location permission to make app functionality works correctly
                showLocationPermissionDialog()
            }
        }
    }

    private fun showLocationPermissionDialog() {
        val dialogView = layoutInflater.inflate(R.layout.custom_dialog_layout, null)
        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_title)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialog_message)
        val dialogButton = dialogView.findViewById<Button>(R.id.dialog_button)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        dialogTitle.text = getString(R.string.location_is_required)
        dialogMessage.text = getString(R.string.location_permission)

        dialogButton.setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
    }



}
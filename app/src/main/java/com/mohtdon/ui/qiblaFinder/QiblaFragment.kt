package com.mohtdon.ui.qiblaFinder

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentQiblaBinding
import com.mohtdon.ui.base.BaseFragment
import com.mohtdon.utilities.LATITUDE
import com.mohtdon.utilities.LONGITUDE
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.atan2
import java.lang.Math.cos
import java.lang.Math.sin
import javax.inject.Inject

@AndroidEntryPoint
class QiblaFragment : BaseFragment<FragmentQiblaBinding>() {
    override val layoutFragmentId: Int = R.layout.fragment_qibla
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    var latitude: Double = 0.0
    var longitude: Double = 0.0
    private var currentAzimuth = 0f

    private var compass: Compass? = null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this


        latitude = sharedPreferences.getString(LATITUDE, "0,0")!!.toDouble()
        longitude = sharedPreferences.getString(LONGITUDE, "0,0")!!.toDouble()


        binding.secondImage.visibility = View.GONE

        setupCompass()

    }

    override fun onStart() {
        super.onStart()
        if (compass != null) {
            compass!!.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (compass != null) {
            compass!!.stop()
        }
    }

    override fun onResume() {
        super.onResume()
        if (compass != null) {
            compass!!.start()
        }
    }

    override fun onStop() {
        super.onStop()
        if (compass != null) {
            compass!!.stop()
        }
    }


    private fun setupCompass() {


        compass = Compass(requireContext())
        val cl: Compass.CompassListener = object : Compass.CompassListener {
            override fun onNewAzimuth(azimuth: Float) {
                adjustGambarDial(azimuth)
                adjustArrowQiblat(azimuth)
            }
        }
        compass!!.setCompassListener(cl)
    }

    fun adjustGambarDial(azimuth: Float) {
        val an: Animation = RotateAnimation(
            -currentAzimuth,
            -azimuth,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        currentAzimuth = azimuth
        an.duration = 500
        an.repeatCount = 0
        an.fillAfter = true
        binding.firstImage.startAnimation(an)
    }

    fun adjustArrowQiblat(azimuth: Float) {

        binding.secondImage.setVisibility(View.VISIBLE)
        val lat1Radians = Math.toRadians(latitude)
        val lon1Radians = Math.toRadians(longitude)
        val lat2Radians = Math.toRadians(21.4224779)
        val lon2Radians = Math.toRadians(39.8251832)

        val deltaLon = lon2Radians - lon1Radians
        val angle = atan2(
            sin(deltaLon) * cos(lat2Radians),
            cos(lat1Radians) * sin(lat2Radians) - sin(lat1Radians) * cos(lat2Radians) * cos(deltaLon)
        )
        val angleDegrees = Math.toDegrees(angle)

        Log.i("ahmed", angleDegrees.toString())

        val an: Animation = RotateAnimation(
            (-currentAzimuth + angleDegrees).toFloat(),
            -azimuth,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        currentAzimuth = azimuth
        an.duration = 500
        an.repeatCount = 0
        an.fillAfter = true

        binding.secondImage.startAnimation(an)
        if (angle > 0) {
            binding.secondImage.setVisibility(View.VISIBLE)
        } else {
            binding.secondImage.setVisibility(View.INVISIBLE)
            binding.secondImage.setVisibility(View.GONE)
        }
    }

}

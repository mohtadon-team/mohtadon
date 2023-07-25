package com.example.straterproject.ui.activities

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.straterproject.R
import com.example.straterproject.databinding.ActivityHomeBinding
import com.example.straterproject.ui.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override val layoutActivityId: Int = R.layout.activity_home
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
navController= Navigation.findNavController(
    this,
    R.id.nav_host_fragment
)
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.reciters -> {
                    navController.navigate(R.id.recitersFragment)
                    true
                }
                R.id.quranFM -> {
                   navController.navigate(R.id.fmRadioFragment)
                    true
                }

                else -> {false}
            }
        }
    }
        }
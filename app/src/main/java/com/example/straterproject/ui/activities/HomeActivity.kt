package com.example.straterproject.ui.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.straterproject.R
import com.example.straterproject.databinding.ActivityHomeBinding
import com.example.straterproject.ui.base.BaseActivity
import com.example.straterproject.utilities.LastPlayedTrackPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override val layoutActivityId: Int = R.layout.activity_home
    lateinit var navController: NavController

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        setTheme(R.style.Theme_StraterProject)
        // Setup the SmoothBottomBar
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.bottom_nav_menu)

        // Attach a listener to handle menu item clicks
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.homeFragment-> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.recitersFragment -> {
                    navController.navigate(R.id.recitersFragment)
                    true
                }
                R.id.quranFragment -> {
                    navController.navigate(R.id.quranFragment)
                    true
                }
                R.id.fmRadioFragment -> {
                    navController.navigate(R.id.fmRadioFragment)
                    true
                }
                R.id.moreFragment->{
                    navController.navigate(R.id.moreFragment)
                    true
                }
                else -> false
            }
        }

        // Get a reference to your SmoothBottomBar
        val smoothBottomBar = binding.bottomNav

        // Set up the SmoothBottomBar with the PopupMenu and NavController
        smoothBottomBar.setupWithNavController(popupMenu.menu, navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

    }
}
package com.example.straterproject.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.domain.entity.QuranResponse
import com.example.straterproject.ui.viewModels.MainActivityViewModel
import com.example.straterproject.R
import com.example.straterproject.databinding.ActivityMainBinding
import com.example.straterproject.ui.UiState
import com.example.straterproject.ui.base.BaseActivity

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutActivityId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       val mainActivityViewModel  : MainActivityViewModel by viewModels()

        binding.button.setOnClickListener {
            mainActivityViewModel.getAllReciter()
        }
        mainActivityViewModel.reciters.observe(this , Observer {
            handleState(it)
        })
    }

    private fun handleState(state  : UiState<QuranResponse>?) {
      when(state){
          is UiState.Loading ->{
            Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
          }

          is UiState.Success ->{
           binding.textView.text = state.data.toString()
          }

          is UiState.Error ->{
               Toast.makeText(this, state.message , Toast.LENGTH_SHORT).show()
              Log.e("logE" ,state.message )
          }
          null -> Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
      }
    }


}
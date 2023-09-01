package com.example.straterproject.ui.reciters.player

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.straterproject.R
import com.example.straterproject.databinding.AudioItemControllerFragmentBinding
import com.example.straterproject.ui.PlayerEvents
import com.example.straterproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AudioItemControllerFragment : BaseFragment<AudioItemControllerFragmentBinding>() {

    override val layoutFragmentId = R.layout.audio_item_controller_fragment
    override val viewModel : AudioItemPlayerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.viewModel = viewModel
       binding.lifecycleOwner = this

              binding.apply {
                  playOrPause.setOnClickListener { viewModel!!.onPlayerEvents(PlayerEvents.PausePlay) }
                  next.setOnClickListener { viewModel!!.onPlayerEvents(PlayerEvents.Next) }
                  previous.setOnClickListener { viewModel!!.onPlayerEvents(PlayerEvents.Previous) }
              }



    }

}
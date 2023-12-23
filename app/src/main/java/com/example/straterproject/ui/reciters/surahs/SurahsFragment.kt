package com.example.straterproject.ui.reciters.surahs

import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.reciters.MoshafEnitity
import com.example.straterproject.R
import com.example.straterproject.databinding.AudioItemControllerFragmentBinding
import com.example.straterproject.databinding.SurahsToPlayFragmentBinding
import com.example.straterproject.player.MediaService
import com.example.straterproject.ui.PlayerEvents
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.reciters.player.AudioItemControllerFragment
import com.example.straterproject.ui.reciters.player.AudioItemPlayerViewModel
import com.example.straterproject.utilities.collect
import com.example.straterproject.utilities.moshafEntityToAudioItemList
import com.example.straterproject.utilities.suraMap
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SurahsFragment : BaseFragment<SurahsToPlayFragmentBinding>(), OnSurahListener {

    override val layoutFragmentId = R.layout.surahs_to_play_fragment
    override val viewModel: SurahViewModel  by viewModels()
    private val audioItemPlayerViewModel : AudioItemPlayerViewModel by activityViewModels()
    private lateinit var surahAdapter: SurahAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       binding.viewModel = viewModel
         binding.playerViewModel = audioItemPlayerViewModel
        surahAdapter = SurahAdapter(this)


        binding.rv.apply {
            adapter = surahAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        collect(viewModel.uiState){
            surahAdapter.suras = it.surahList
            audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.AddPlaylist(moshafEntityToAudioItemList(it.moshaf)))
        }

        binding.include.openPlayerController.setOnClickListener {
            openPlayerController()
        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSurahClick(position: Int) {
        audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.GoToSpecificItem(position))
        audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.PausePlay)
        openPlayerController()

    }

    private fun openPlayerController(){
        val audioItemControllerFragment = AudioItemControllerFragment()
        audioItemControllerFragment.show(childFragmentManager,audioItemControllerFragment.tag)
    }
}


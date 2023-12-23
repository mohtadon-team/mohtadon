package com.example.straterproject.ui.radio


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.radio.RadioEntity
import com.example.domain.entity.reciters.MoshafEnitity
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentFmRadioBinding
import com.example.straterproject.player.MediaService
import com.example.straterproject.ui.PlayerEvents
import com.example.straterproject.ui.UiState
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.reciters.player.AudioItemPlayerViewModel
import com.example.straterproject.ui.reciters.surahs.SurahAdapter
import com.example.straterproject.ui.reciters.surahs.SurahsFragmentArgs
import com.example.straterproject.utilities.LastPlayedTrackPreference
import com.example.straterproject.utilities.collect
import com.example.straterproject.utilities.collectLast
import com.example.straterproject.utilities.moshafEntityToAudioItemList
import com.example.straterproject.utilities.radioEntityToAudioItemList
import com.example.straterproject.utilities.suraMap
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class FmRadioFragment : BaseFragment<FragmentFmRadioBinding>() , OnRadioStationListener{

    @Inject
    lateinit var lastPlayedTrackPreference: LastPlayedTrackPreference
    override val layoutFragmentId = R.layout.fragment_fm_radio
    override val viewModel: RadioViewModel by viewModels ()
    private val audioItemPlayerViewModel: AudioItemPlayerViewModel by activityViewModels()

    private lateinit var radioAdapter: RadioAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.viewModel = viewModel
        binding.playerViewModel = audioItemPlayerViewModel
        binding.lifecycleOwner = this

        radioAdapter = RadioAdapter(this , lastPlayedTrackPreference)

        binding.rv.apply {
            adapter = radioAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        collect(viewModel.uiState){
            radioAdapter.radioStations = it.radioStations
            audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.AddPlaylist(radioEntityToAudioItemList(it.radioStations)))
        }

//        collectLast(viewModel.uiEffect){
//            when(it){
//                RadioUiEffect.Back ->  activity?.onBackPressed()
//                RadioUiEffect.SearchCancel -> binding.searchEditTxt.text
//            }
//        }

        binding.include.playOrPause.setOnClickListener {
            audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.PausePlay)
        }

    }

    override fun onStationClick(position: Int) {
        audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.GoToSpecificItem(position))
        lastPlayedTrackPreference.lastPlayedTrackId = position.toLong()
        radioAdapter.notifyItemChanged(position)
    }

}
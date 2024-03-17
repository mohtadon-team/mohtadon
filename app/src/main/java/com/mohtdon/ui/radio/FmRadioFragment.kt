package com.mohtdon.ui.radio


import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentFmRadioBinding
import com.mohtdon.ui.PlayerEvents
import com.mohtdon.ui.base.BaseFragment
import com.mohtdon.ui.reciters.player.AudioItemPlayerViewModel
import com.mohtdon.utilities.LastPlayedTrackPreference
import com.mohtdon.utilities.collect
import com.mohtdon.utilities.radioEntityToAudioItemList
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class FmRadioFragment : BaseFragment<FragmentFmRadioBinding>() , OnRadioStationListener {

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
            audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.AddPlaylist(
                radioEntityToAudioItemList(it.radioStations)
            ))
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
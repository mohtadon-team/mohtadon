package com.mohtdon.ui.reciters.surahs

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.SurahsToPlayFragmentBinding
import com.mohtdon.ui.PlayerEvents
import com.mohtdon.ui.base.BaseFragment
import com.mohtdon.ui.reciters.player.AudioItemControllerFragment
import com.mohtdon.ui.reciters.player.AudioItemPlayerViewModel
import com.mohtdon.utilities.collect
import com.mohtdon.utilities.moshafEntityToAudioItemList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SurahsFragment : BaseFragment<SurahsToPlayFragmentBinding>(), OnSurahListener {

    override val layoutFragmentId = R.layout.surahs_to_play_fragment
    override val viewModel: SurahViewModel by viewModels()
    private val audioItemPlayerViewModel: AudioItemPlayerViewModel by activityViewModels()
    private lateinit var surahAdapter: SurahAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val args = SurahsFragmentArgs.fromBundle(requireArguments())
        val reciterName = args.moshaf.reciterName
        val moshafType = args.moshaf.moshafName
        binding.moshafType.text = moshafType
        binding.textView4.text = reciterName


        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        binding.playerViewModel = audioItemPlayerViewModel
        surahAdapter = SurahAdapter(this)


        binding.rv.apply {
            adapter = surahAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        collect(viewModel.uiState) {
            surahAdapter.suras = it.surahList
            audioItemPlayerViewModel.onPlayerEvents(
                PlayerEvents.AddPlaylist(
                    moshafEntityToAudioItemList(it.moshaf)
                )
            )
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

    private fun openPlayerController() {
        val audioItemControllerFragment = AudioItemControllerFragment()
        audioItemControllerFragment.show(childFragmentManager, audioItemControllerFragment.tag)
    }
}


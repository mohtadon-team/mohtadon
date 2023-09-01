package com.example.straterproject.ui.reciters.surahs

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.reciters.MoshafEnitity
import com.example.domain.entity.reciters.SuraEntity
import com.example.straterproject.R
import com.example.straterproject.databinding.SurahsToPlayFragmentBinding
import com.example.straterproject.player.AudioItem
import com.example.straterproject.player.MediaService
import com.example.straterproject.ui.PlayerEvents
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.reciters.player.AudioItemPlayerViewModel
import com.example.straterproject.ui.reciters.reciterInfo.RecitersFragmentDirections
import com.example.straterproject.utilities.moshafEntityToAudioItemList
import com.example.straterproject.utilities.suraMap
import dagger.hilt.android.AndroidEntryPoint
import java.text.FieldPosition

@AndroidEntryPoint
class SurahsFragment : BaseFragment<SurahsToPlayFragmentBinding>() ,OnSurahListener {

    override val layoutFragmentId = R.layout.surahs_to_play_fragment
    override val viewModel: AudioItemPlayerViewModel by activityViewModels ()
    private val args: SurahsFragmentArgs by navArgs()

    private lateinit var moshaf :MoshafEnitity

    private lateinit var surahAdapter: SurahAdapter
    private var isServiceRunning = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.viewModel = viewModel
        moshaf = args.moshaf
        surahAdapter = SurahAdapter(this)
        val surahList = moshaf.surah_list.split(',').map { suraMap[it] }

        surahAdapter.suras = surahList
        binding.rv.apply {
            adapter = surahAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        viewModel.onPlayerEvents(PlayerEvents.AddPlaylist(moshafEntityToAudioItemList(moshaf)))



    }
    override fun onDestroy() {
        super.onDestroy()
        requireActivity().stopService(Intent(requireContext(), MediaService::class.java))
        isServiceRunning = false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startServiceIfNotStarted() {
        if (!isServiceRunning) {
            val intent = Intent(requireContext(), MediaService::class.java)
            requireActivity().startForegroundService(intent)
            isServiceRunning = true
        }
    }

    override fun onSurahClick(position: Int) {
        viewModel.onPlayerEvents(PlayerEvents.GoToSpecificItem(position))
        findNavController().navigate(R.id.action_surahsFragment_to_audioItemControllerFragment)
    }
}


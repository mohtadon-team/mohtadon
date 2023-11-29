package com.example.straterproject.ui.reciters.surahs

import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
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
import com.example.straterproject.utilities.moshafEntityToAudioItemList
import com.example.straterproject.utilities.suraMap
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SurahsFragment : BaseFragment<SurahsToPlayFragmentBinding>(), OnSurahListener {

    override val layoutFragmentId = R.layout.surahs_to_play_fragment

    private val  audioItemPlayerViewModel: AudioItemPlayerViewModel by activityViewModels()
    override val viewModel :SurahViewModel by viewModels()

    private val args: SurahsFragmentArgs by navArgs()

    private lateinit var moshaf: MoshafEnitity

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

        audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.AddPlaylist(moshafEntityToAudioItemList(moshaf)))


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
        audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.AddPlaylist(moshafEntityToAudioItemList(moshaf)))
        audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.GoToSpecificItem(position))


        val audioItemControllerFragment = AudioItemControllerFragment()
        audioItemControllerFragment.show(childFragmentManager,audioItemControllerFragment.tag)

//        val bottomSheetDialog = BottomSheetDialog(requireActivity())
//        val sheetBinding = AudioItemControllerFragmentBinding.inflate(LayoutInflater.from(requireActivity()))
//        bottomSheetDialog.setContentView(sheetBinding.root)
////
//        val  bottomSheetBehavior = BottomSheetBehavior.from(sheetBinding.root)
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//
//        val layout = sheetBinding.bottomSheet
//
//        assert(layout!=null)
//
//        layout.minimumHeight= Resources.getSystem().displayMetrics.heightPixels
//
//        bottomSheetDialog.show()

    }
}


package com.example.straterproject.ui.fragments


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.dataSource.remote.response.reciters.Reciter
import com.example.domain.entity.reciters.MoshafEnitity
import com.example.domain.entity.reciters.ReciterEntity
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentRecitersBinding
import com.example.straterproject.player.AudioItem
import com.example.straterproject.player.MediaService
import com.example.straterproject.ui.PlayerEvents
import com.example.straterproject.ui.UiState
import com.example.straterproject.ui.adapters.OnMoshafListener

import com.example.straterproject.ui.adapters.RecitersAdapter
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.viewModels.AudioItemPlayerViewModel
import com.example.straterproject.ui.viewModels.RecitersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.common_header.view.*

@AndroidEntryPoint
class RecitersFragment : BaseFragment<FragmentRecitersBinding>() ,OnMoshafListener {
    override val layoutFragmentId= R.layout.fragment_reciters
    override val viewModel : RecitersViewModel by viewModels()

    private lateinit var recitersAdapter: RecitersAdapter
    private var isServiceRunning = false

    val audioItemPlayerViewModel :AudioItemPlayerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recitersAdapter = RecitersAdapter(requireContext() , this)

        binding.recitersRv.apply {
            adapter = recitersAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        viewModel.reciters.observe(viewLifecycleOwner){
            handleStae(it)
        }
        onClickActions()

    }

    private fun handleStae(state: UiState<List<ReciterEntity>>?) {
       when(state){
           is UiState.Error -> {
               Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
           }
           is UiState.Success -> {
               recitersAdapter.reciters = state.data!!
           }
           UiState.Loading -> {
               Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
           }

           else -> {}
       }
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().stopService(Intent(requireContext(), MediaService::class.java))
        isServiceRunning = false
    }
    private fun onClickActions() {
        binding.commonHeader.header_title.setText("القراء")
        binding.commonHeader.search_view.setOnSearchClickListener {
            binding.commonHeader.header_title.visibility = View.GONE

        }
        binding.commonHeader.search_view?.setOnCloseListener {
            binding.commonHeader.header_title.visibility = View.VISIBLE
            return@setOnCloseListener false
        }
        binding.commonHeader.search_view?.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return  false
            }


            override fun onQueryTextChange(p0: String?): Boolean {
                filterList(p0)
                return true
            }

        }
        )
    }
    private fun filterList(query: String?) {

//        val filteredList = if (query.isNullOrEmpty()) {
//            viewModel.reciters.value ?: emptyList()
//        } else {
//            viewModel.reciters.value?.filter { reciter ->
//                surah.name.contains(query, ignoreCase = true)
//            } ?: emptyList()
//        }
//
//        quranAdapter.setSurahList(filteredList)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startServiceIfNotStarted() {
        if (!isServiceRunning) {
            val intent = Intent(requireContext(), MediaService::class.java)
            requireActivity().startForegroundService(intent)
            isServiceRunning = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMoshafClick(moshaf: MoshafEnitity) {
        startServiceIfNotStarted()
       val audioItem = AudioItem(
           reciter = moshaf.reciterName ,
           moshaf = moshaf.name,
           image = ""  ,
           source = moshaf.server.plus("003.mp3")
       )
       // Toast.makeText(requireContext(), audioItem.source, Toast.LENGTH_SHORT).show()
        audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.AddNewAudioItem(audioItem))

    }


}
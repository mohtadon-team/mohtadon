package com.example.straterproject.ui.reciters.reciterInfo


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.reciters.MoshafEnitity
import com.example.domain.entity.reciters.ReciterEntity
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentRecitersBinding
import com.example.straterproject.player.AudioItem
import com.example.straterproject.player.MediaService
import com.example.straterproject.ui.PlayerEvents
import com.example.straterproject.ui.UiState

import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.reciters.player.AudioItemPlayerViewModel
import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.android.synthetic.main.common_header.view.*

@AndroidEntryPoint
class RecitersFragment : BaseFragment<FragmentRecitersBinding>() , OnMoshafListener {
    override val layoutFragmentId= R.layout.fragment_reciters
    override val viewModel : RecitersViewModel by viewModels()
      val audioItemPlayerViewModel : AudioItemPlayerViewModel by activityViewModels()
    private lateinit var recitersAdapter: RecitersAdapter


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

        binding.button2.setOnClickListener{
            audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.AddNewAudioItem(
                AudioItem(
                    reciterAndHisMoshaf = "dsuy",
                    surah =  "surah",
                    image = "" ,
                    source = "https://backup.qurango.net/radio/sahabah"
                )
            ))
        }

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


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMoshafClick(moshaf: MoshafEnitity) {

        val action = RecitersFragmentDirections.actionRecitersFragmentToSurahsFragment(moshaf )
        findNavController().navigate(action)

//        startServiceIfNotStarted()
//       val audioItem = AudioItem(
//           reciter = moshaf.reciterName ,
//           moshaf = moshaf.name,
//           image = ""  ,
//           source = moshaf.server.plus("003.mp3")
//       )
//       // Toast.makeText(requireContext(), audioItem.source, Toast.LENGTH_SHORT).show()
//        audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.AddNewAudioItem(audioItem))

    }


}
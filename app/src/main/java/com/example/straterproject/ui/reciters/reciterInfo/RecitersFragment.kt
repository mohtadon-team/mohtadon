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
import androidx.media3.common.util.Log
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
import com.example.straterproject.ui.radio.RadioUiEffect
import com.example.straterproject.ui.reciters.player.AudioItemControllerFragment
import com.example.straterproject.ui.reciters.player.AudioItemPlayerViewModel
import com.example.straterproject.utilities.collect
import com.example.straterproject.utilities.collectLast
import com.example.straterproject.utilities.moshafEntityToAudioItemList
import com.example.straterproject.utilities.radioEntityToAudioItemList
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecitersFragment : BaseFragment<FragmentRecitersBinding>() , OnMoshafListener {
    override val layoutFragmentId= R.layout.fragment_reciters
    override val viewModel : RecitersViewModel by viewModels()
    private lateinit var recitersAdapter: RecitersAdapter

    private val audioItemPlayerViewModel : AudioItemPlayerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        recitersAdapter = RecitersAdapter(requireContext() , this)

        binding.rv.apply {
            adapter = recitersAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }


        collect(viewModel.uiState){
            recitersAdapter.reciters = it.reciters
        }

        collectLast(viewModel.uiEffect){
            when(it){
                ReciterUiEffect.Back ->  activity?.onBackPressed()
                ReciterUiEffect.SearchCancel -> {}
            }
        }


    }




    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMoshafClick(moshaf: MoshafEnitity) {
        val action = RecitersFragmentDirections.actionRecitersFragmentToSurahsFragment(moshaf)
        findNavController().navigate(action)
       // audioItemPlayerViewModel.onPlayerEvents(PlayerEvents.GoToSpecificItem(moshaf.id))
    }




}
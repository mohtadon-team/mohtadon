package com.mohtdon.ui.reciters.reciterInfo


import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohtdon.domain.models.reciters.MoshafEnitity
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentRecitersBinding
import com.mohtdon.ui.base.BaseFragment
import com.mohtdon.ui.reciters.player.AudioItemPlayerViewModel
import com.mohtdon.utilities.collect
import com.mohtdon.utilities.collectLast
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
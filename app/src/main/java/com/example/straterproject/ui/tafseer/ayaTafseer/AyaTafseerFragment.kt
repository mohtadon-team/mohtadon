package com.example.straterproject.ui.tafseer.ayaTafseer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentAyaTafseerBinding
import com.example.straterproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AyaTafseerFragment : BaseFragment<FragmentAyaTafseerBinding>() {
    override val layoutFragmentId: Int = R.layout.fragment_aya_tafseer
    override val viewModel: AyaTafseerViewModel by viewModels()
    private val args: AyaTafseerFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        setDuaaText(args.position)
        binding.ayaTafseerScrollView.post(Runnable {
            binding.ayaTafseerScrollView.smoothScrollTo(
                0, binding.ayaTafseerText.getBottom()
            )
        })

    }

    private fun setDuaaText(position: Int) {
        var ayaTafseerText = viewModel.getAyaTafseerText(position)
        binding.ayaTafseerText.text = ayaTafseerText
    }
}
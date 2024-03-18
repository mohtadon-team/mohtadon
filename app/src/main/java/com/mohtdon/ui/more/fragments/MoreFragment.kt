package com.mohtdon.ui.more.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mohtdon.mohtdon.BuildConfig
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentMoreBinding
import com.mohtdon.ui.base.BaseFragment
import com.mohtdon.ui.more.adapters.MoreListAdapter
import com.mohtdon.ui.more.viewModels.MoreViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoreFragment : BaseFragment<FragmentMoreBinding>(), MoreListAdapter.OnNameGridViewListener,
    AdapterView.OnItemClickListener {
    override val layoutFragmentId: Int = R.layout.fragment_more
    override val viewModel: MoreViewModel by viewModels()
    lateinit var listAdapter: MoreListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        setMoreList()
        binding.pageHeader.headerTitle.text = "المزيد"
        binding.pageHeader.search.visibility = View.GONE
    }

    private fun setMoreList() {
        val moreItems = viewModel.moreListItems.value.moreList
        listAdapter = MoreListAdapter(this, activity, moreItems)
        binding.moreGV.adapter = listAdapter
        binding.moreGV.onItemClickListener = this

    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        onItemClick(p2)
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                findNavController().navigate(R.id.action_moreFragment_to_qiblaFragment)
            }

            1 -> {
                findNavController().navigate(R.id.action_moreFragment_to_namesOfAllahFragment)
            }

            2 -> {
                findNavController().navigate(R.id.action_moreFragment_to_rosaryFragment)

            }

            3 -> {
                findNavController().navigate(R.id.action_moreFragment_to_shehadaFragment)

            }

            4 -> {
                try {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "مهتدون")
                    var shareMessage = "\nLet me recommend you mohtdon application\n\n"
                    shareMessage = "     ${
                        shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID
                    }\n".trimIndent()
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                    startActivity(Intent.createChooser(shareIntent, "choose one"))
                } catch (e: Exception) {
                    e.stackTrace
                }
            }

            5 -> {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://www.youtube.com/live/PEmRPDJ9I8M?si=OqlRxj90ogOxD59R")
                    setPackage("com.google.android.youtube")
                }
                startActivity(intent)

            }

            6 -> {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://www.youtube.com/live/EB0Y1ztWnic?si=U-1uZH0FBjzjr_JN")
                    setPackage("com.google.android.youtube")
                }
                startActivity(intent)
            }

            7 -> {
                findNavController().navigate(R.id.action_moreFragment_to_hijriCalenderFragment)
            }
        }
    }


}
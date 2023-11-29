package com.example.straterproject.ui.reciters.player

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.domain.entity.reciters.MoshafEnitity
import com.example.straterproject.R
import com.example.straterproject.databinding.AudioItemControllerFragmentBinding
import com.example.straterproject.ui.PlayerEvents
import com.example.straterproject.ui.base.BaseDialogFragment
import com.example.straterproject.ui.base.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AudioItemControllerFragment (
): BaseDialogFragment<AudioItemControllerFragmentBinding>() {

    override val layoutFragmentId = R.layout.audio_item_controller_fragment
    override val viewModel : AudioItemPlayerViewModel by viewModels()

    private lateinit var dialog: BottomSheetDialog
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        val layout = dialog.findViewById<CoordinatorLayout>(R.id.coordinatorlayout_bottom_sheet)
        layout!!.minimumHeight= Resources.getSystem().displayMetrics.heightPixels


       binding.viewModel = viewModel
       binding.lifecycleOwner = this

              binding.apply {
                  playOrPause.setOnClickListener { viewModel!!.onPlayerEvents(PlayerEvents.PausePlay) }
                  next.setOnClickListener { viewModel!!.onPlayerEvents(PlayerEvents.Next) }
                  previous.setOnClickListener { viewModel!!.onPlayerEvents(PlayerEvents.Previous) }
              }



    }

}
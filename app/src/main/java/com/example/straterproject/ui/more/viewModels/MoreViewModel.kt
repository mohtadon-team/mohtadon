package com.example.straterproject.ui.more.viewModels

import android.content.Context
import com.example.straterproject.R
import com.example.straterproject.ui.base.BaseViewModel
import com.example.straterproject.ui.more.MoreItemModel
import com.example.straterproject.ui.more.MoreListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
@HiltViewModel
class MoreViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {
    private val _moreList = MutableStateFlow(MoreListUiState())
    val moreListItems = _moreList.asStateFlow()

    init {
        getList()
    }

    private fun getList() {

        val moreListItems = ArrayList<MoreItemModel>()

        moreListItems.add(MoreItemModel("القبله", R.drawable.qibla))
        moreListItems.add(MoreItemModel("اسماء الله الحسني",R.drawable.names_of_allah))
        moreListItems.add(MoreItemModel("السبحه",R.drawable.sebha_icon))
        moreListItems.add(MoreItemModel("الشهاده",R.drawable.shehada_icon))
        moreListItems.add(MoreItemModel("مشاركه بالاجر",R.drawable.qibla_blue_icon))
        moreListItems.add(MoreItemModel("مكه لايف",R.drawable.qibla_blue_icon))
        moreListItems.add(MoreItemModel("المدينه لايف",R.drawable.qibla_blue_icon))
        moreListItems.add(MoreItemModel("التقويم الهجري",R.drawable.qibla_blue_icon))



        _moreList.update {
            it.copy(
                moreList = moreListItems
            )
        }

    }
}
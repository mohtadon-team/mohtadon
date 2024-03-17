package com.mohtdon.ui.quran.quranViewPager

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.mohtdon.data.dataSource.remote.response.quran.models.SavedPageModel
import com.mohtdon.ui.base.BaseFragment
import com.mohtdon.ui.quran.quranPage.QuranPagerAdapter
import com.mohtdon.ui.viewModels.QuranViewModel
import com.mohtdon.utilities.TOTAL_QURAN_PAGES_NUMBER
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentQuranViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class QuranViewPagerFragment : BaseFragment<FragmentQuranViewPagerBinding>() {

    override val layoutFragmentId: Int = R.layout.fragment_quran_view_pager
    override val viewModel: QuranViewModel by viewModels()


    private val args: QuranViewPagerFragmentArgs by navArgs()

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this


        if (sharedPreferences.getInt("last_page_flag", 0) == 1) {
            initializeViewPager(sharedPreferences.getInt("last_page", 0))
        } else {
            initializeViewPager(args.startPageNum)
            setInt("last_page", args.startPageNum)
        }

        binding.quranHeader.search.setOnClickListener {
            findNavController().navigate(R.id.quranSearchFragment)
        }

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setSuraNameHeaderandInSP(position)
                setInt("last_page", TOTAL_QURAN_PAGES_NUMBER - position)
            }
        })

        initailzeSavedItemsList()

        binding.quranHeader.saveMark.setOnClickListener {
            setSavedPage()
        }
    }

    private fun initializeViewPager(pageNum: Int) {
        binding.viewPager.adapter = QuranPagerAdapter(requireActivity())
        binding.viewPager.setCurrentItem(TOTAL_QURAN_PAGES_NUMBER - pageNum, false)
        setSuraNameHeaderandInSP(binding.viewPager.currentItem)

    }

    private fun setSuraNameHeaderandInSP(position: Int) {
        var suraName = ""
        viewLifecycleOwner.lifecycleScope.launch {
            suraName = viewModel.getSoraName(TOTAL_QURAN_PAGES_NUMBER - position).soraName
            binding.quranHeader.headerTitle.text = suraName
            putSuraNameInSp(suraName)
        }
    }


    private fun putSuraNameInSp(suraName: String) {
        val editor = sharedPreferences.edit()
        editor.putString("suraName", suraName)
        editor.commit()
        editor.apply()
        binding.quranHeader.saveMark.visibility = View.VISIBLE
    }

    private fun initailzeSavedItemsList() {
        if (getSavedPagesList() == null) {
            setInt("number_of_item_to_save", 1)
            val savedPagesList: List<SavedPageModel> = mutableListOf()
            val savedSuraNameList: List<String> = mutableListOf()
            setList("saved_page", savedPagesList)
            setList("saved_sura_names", savedSuraNameList)
        }
    }

    private fun setSavedPage() {
        val numberOfItemToSave = sharedPreferences.getInt("number_of_item_to_save", 0)
        val currentPageNum = TOTAL_QURAN_PAGES_NUMBER - binding.viewPager.currentItem

        if (getSavedPagesList()?.size == 0) {
            val savedPageslist = ArrayList<SavedPageModel>()
            val savedSuraNamesList = ArrayList<String>()
            savedPageslist.add(SavedPageModel(currentPageNum))
            viewLifecycleOwner.lifecycleScope.launch {
                savedSuraNamesList.add(viewModel.getSoraName(currentPageNum).soraName)
                setList("saved_sura_names", savedSuraNamesList)
            }
            setList("saved_page", savedPageslist)
            setInt("number_of_item_to_save", 1)
        } else {
            if (numberOfItemToSave > 10) {
                val savedPagesList = getSavedPagesList()
                val savedSuraNamesList = getSavedSurNameList()
                savedPagesList!![0] = SavedPageModel(currentPageNum)
                viewLifecycleOwner.lifecycleScope.launch {
                    savedSuraNamesList!![0] = viewModel.getSoraName(currentPageNum).soraName
                    setList("saved_sura_names", savedSuraNamesList)
                }
                setList("saved_page", savedPagesList)
                setInt("number_of_item_to_save", 1)
            } else {
                if (getSavedPagesList()!!.size < 10) {
                    val savedPagesList = getSavedPagesList()
                    val savedSuraNamesList = getSavedSurNameList()
                    viewLifecycleOwner.lifecycleScope.launch {
                        savedSuraNamesList!!.add(viewModel.getSoraName(currentPageNum).soraName)
                        setList("saved_sura_names", savedSuraNamesList)
                    }
                    savedPagesList!!.add(SavedPageModel(currentPageNum))
                    setList("saved_page", savedPagesList)
                    setInt("number_of_item_to_save", numberOfItemToSave + 1)

                } else {
                    if (numberOfItemToSave == 10) {

                        val savedPagesList = getSavedPagesList()
                        val savedSuraNamesList = getSavedSurNameList()
                        viewLifecycleOwner.lifecycleScope.launch {
                            savedSuraNamesList!![0] = viewModel.getSoraName(currentPageNum).soraName
                            setList("saved_sura_names", savedSuraNamesList)
                        }
                        savedPagesList!![0] = SavedPageModel(currentPageNum)
                        setList("saved_page", savedPagesList)
                        setInt("number_of_item_to_save", 1)
                    } else {
                        val savedPagesList = getSavedPagesList()
                        val savedSuraNamesList = getSavedSurNameList()
                        viewLifecycleOwner.lifecycleScope.launch {
                            savedSuraNamesList!![numberOfItemToSave] =
                                viewModel.getSoraName(currentPageNum).soraName
                            setList("saved_sura_names", savedSuraNamesList)
                        }
                        savedPagesList!![numberOfItemToSave] = SavedPageModel(currentPageNum)
                        setList("saved_page", savedPagesList)
                        setInt("number_of_item_to_save", numberOfItemToSave + 1)
                    }

                }
            }
        }


    }

    fun <T> setList(key: String, list: List<T>) {
        val gson = Gson()
        val json = gson.toJson(list)
        setString(key, json)
    }

    fun setString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()
        editor.apply()
    }

    fun setInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.commit()
        editor.apply()
    }


    fun getSavedPagesList(): MutableList<SavedPageModel>? {
        var arrayItems: MutableList<SavedPageModel> = mutableListOf()

        val serializedObject = sharedPreferences.getString("saved_page", null)

        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<SavedPageModel>>() {}.type
            arrayItems = gson.fromJson(serializedObject, type)

            return arrayItems
        }

        return arrayItems
    }

    fun getSavedSurNameList(): MutableList<String>? {
        var arrayItems: MutableList<String> = mutableListOf()

        val serializedObject = sharedPreferences.getString("saved_sura_names", null)

        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<String>>() {}.type
            arrayItems = gson.fromJson(serializedObject, type)

            return arrayItems
        }

        return arrayItems
    }
}
package com.example.straterproject.ui.quran.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.data.dataSource.remote.response.quran.models.SavedPageModel
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentQuranViewPagerBinding
import com.example.straterproject.ui.quran.adapters.QuranPagerAdapter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuranViewPagerFragment : Fragment() {

    private val args: QuranViewPagerFragmentArgs by navArgs()

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    companion object {
        private var _binding:FragmentQuranViewPagerBinding ? = null
        val mBinding get() = _binding!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            _binding = FragmentQuranViewPagerBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val quranPagesAdapter = QuranPagerAdapter(requireActivity())
        val startPage: Int = args.startPageNum
        viewPager.adapter = quranPagesAdapter
        //set current item
        viewPager.currentItem = 604 - startPage
        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mBinding.quranHeader.headerTitle.text = QuranPageFragment.pageData.soraName
            }
        })
        val suraName=QuranPageFragment.pageData.soraName
        val editor = sharedPreferences.edit()
        editor.putString("suraName",suraName)
        editor.commit()
        editor.apply()
        mBinding.quranHeader.saveMark.visibility = View.VISIBLE
        if (getList() == null) {
            setInt("number_of_item_to_save", 1)
            val list: List<SavedPageModel> = mutableListOf()
            setList("saved_page", list)
        }

        mBinding.quranHeader.saveMark.setOnClickListener {
            var numberOfItemToSave = sharedPreferences.getInt("number_of_item_to_save", 0)
            val currentPageNum = 604 - viewPager.currentItem

            if (getList()?.size == 0) {
                val list = ArrayList<SavedPageModel>()
                list.add(SavedPageModel(currentPageNum))
                setList("saved_page", list)
                setInt("number_of_item_to_save", 1)
            } else {
                if (numberOfItemToSave > 10) {

                    val list = getList()
                    list!![0] = SavedPageModel(currentPageNum)
                    setList("saved_page", list)
                    setInt("number_of_item_to_save", 1)
                } else {
                    if (getList()!!.size < 10) {

                        Log.i("ahmed", numberOfItemToSave.toString())
                        Log.i("ahmed", getList()?.size.toString())
                        Log.i("ahmed", getList().toString())

                        val list = getList()
                        list!!.add(SavedPageModel(currentPageNum))

                        setList("saved_page", list)
                        setInt("number_of_item_to_save", numberOfItemToSave + 1)

                    } else {
                        if (numberOfItemToSave == 10) {

                            val list = getList()
                            list!![0] = SavedPageModel(currentPageNum)
                            setList("saved_page", list)
                            setInt("number_of_item_to_save", 1)
                        } else {
                            val list = getList()
                            list!![numberOfItemToSave] = SavedPageModel(currentPageNum)
                            setList("saved_page", list)
                            setInt("number_of_item_to_save", numberOfItemToSave + 1)
                        }

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


    fun getList(): MutableList<SavedPageModel>? {
        var arrayItems: MutableList<SavedPageModel> = mutableListOf()

        val serializedObject = sharedPreferences.getString("saved_page", null)

        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<SavedPageModel>>() {}.type
            arrayItems = gson.fromJson(serializedObject, type)

            return arrayItems
        }

        return null
    }


}
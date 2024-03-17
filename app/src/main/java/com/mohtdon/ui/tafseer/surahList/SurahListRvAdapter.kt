package com.mohtdon.ui.tafseer.surahList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.mohtdon.databinding.SurahListRvItemBinding


class SurahListRvAdapter(
    private val listener: OnAyaListRvListener, surahItems: List<SurahModel>
) : RecyclerView.Adapter<SurahListRvAdapter.SurahListRvViewHolder>() {

    class SurahListRvViewHolder(val binding: SurahListRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    var surahListItems: List<SurahModel> = surahItems


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahListRvViewHolder {
        val binding =
            SurahListRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SurahListRvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return surahListItems.size
    }

    override fun onBindViewHolder(holder: SurahListRvViewHolder, position: Int) {
        val item = surahListItems[position]
        holder.binding.apply {

            surahNumberText.text = item.surahId.toString()
            surahTypeText.text = item.surahRevelationPlace

            surahNameTafseer.text = item.surahName

            if(item.surahNumberOfAyat > 10 ){
                "${item.surahNumberOfAyat} ايه  ".also { surahNumberOfAyatText.text = it }
            }else {
                "${item.surahNumberOfAyat} ايات  ".also { surahNumberOfAyatText.text = it }
            }
        }

        holder.binding.root.setOnClickListener {
            listener.onItemclick(position)
        }

    }


}

interface OnAyaListRvListener {
    fun onItemclick(position: Int)
}

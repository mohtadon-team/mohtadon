package com.mohtdon.ui.tafseer.surahTafseer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.mohtdon.databinding.SurahTafseerRvItemBinding

class SurahTafseerRvAdapter(
     surahTafseer: List<SurahTafseerRvModel>
) : RecyclerView.Adapter<SurahTafseerRvAdapter.SurahTafseerRvViewHolder>() {

    class SurahTafseerRvViewHolder(val binding: SurahTafseerRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    var surahTafseerItems: List<SurahTafseerRvModel> = surahTafseer


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahTafseerRvViewHolder {
        val binding =
            SurahTafseerRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SurahTafseerRvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return surahTafseerItems.size
    }

    override fun onBindViewHolder(holder: SurahTafseerRvViewHolder, position: Int) {
        val item = surahTafseerItems[position]
        holder.binding.apply {
            surahTafseerName.text = item.surahName
            ( " ايه "+item.ayahNumber).also { surahTafseerAyahNumber.text = it }
            ayahText.text = item.ayahText
            ayahTafseerText.text = item.ayahTafseer
        }

    }


}
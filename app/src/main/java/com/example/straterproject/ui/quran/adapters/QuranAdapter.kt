package com.example.straterproject.ui.quran.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.example.straterproject.databinding.SurahItemBinding
import com.example.domain.entity.Surah
import com.example.straterproject.ui.interfaces.IonSurahClick

class QuranAdapter : RecyclerView.Adapter<QuranAdapter.QuranHolder>() {
    private val surahList: MutableList<Surah> = mutableListOf()
    var ionSurahClick:IonSurahClick?=null
    fun setSurahList(surahList: List<Surah>) {
        this.surahList.clear()
        this.surahList.addAll(surahList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuranHolder {
        val binding = SurahItemBinding.inflate(LayoutInflater.from(parent.context), null, false)
        return QuranHolder(binding)
    }

    override fun onBindViewHolder(holder: QuranHolder, position: Int) {
        val surah = surahList[position]
        holder.bind(surah)
    }

    override fun getItemCount(): Int {
        return surahList.size
    }

   inner class QuranHolder(private val binding: SurahItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(surah: Surah) {
            binding.tvSurahName.text = surah.nameArabic
            when(surah.revelationPlace) {
                "makkah" -> binding.tvSurahType.text = "مكيه"
                "madinah" -> binding.tvSurahType.text = "مدنيه"
            }
            binding.tvSurahNum.text= surah.id.toString()
            itemView.setOnClickListener {
                ionSurahClick?.clickSurah(surah)
            }

        }
    }
}

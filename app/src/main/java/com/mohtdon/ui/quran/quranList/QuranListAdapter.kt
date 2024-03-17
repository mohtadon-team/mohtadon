package com.mohtdon.ui.quran.quranList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.domain.models.quran.Surah
import com.mohtdon.mohtdon.databinding.SurahItemBinding
import com.mohtdon.ui.quran.interfaces.IonSurahClick

class QuranListAdapter(private val listener: IonSurahClick) :
    RecyclerView.Adapter<QuranListAdapter.QuranHolder>() {

    private var surahList: List<Surah> = arrayListOf()
    fun setSurahList(newsurahList: List<Surah>) {
        surahList = newsurahList
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

    inner class QuranHolder(private val binding: SurahItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(surah: Surah) {
            binding.tvSurahName.text = surah.nameArabic
            when (surah.revelationPlace) {
                "makkah" -> binding.tvSurahType.text = "مكيه"
                "madinah" -> binding.tvSurahType.text = "مدنيه"
            }
            binding.tvSurahNum.text = surah.id.toString()
            if (surah.verses_count <= 10) {
                "${surah.verses_count} ايات".also { binding.tvVerseNum.text = it }
            } else {
                "${surah.verses_count} ايه".also { binding.tvVerseNum.text = it }
            }
            if ((surah.pages[1] - surah.pages[0]) <= 10) {
                if ((surah.pages[1] - surah.pages[0]) == 0) {
                    "${1} صفحات ".also { binding.tvNumberOfPages.text = it }
                } else {
                    "${(surah.pages[1] - surah.pages[0])} صفحات ".also {
                        binding.tvNumberOfPages.text = it
                    }
                }
            } else {
                "${(surah.pages[1] - surah.pages[0])} صفحة ".also { binding.tvNumberOfPages.text = it }
            }
            binding.surahInfoLayout.setOnClickListener {
                listener.clickSurah(surah)
            }

        }
    }
}

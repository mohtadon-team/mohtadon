package com.mohtdon.ui.hadith.hadithBookChapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.mohtdon.databinding.HadithListRvItemBinding

class HadithBookChaptersRvAdapter(
    private val listener: OnHadithListRvListener,
    private val chapters:List<String>
    ): RecyclerView.Adapter<HadithBookChaptersRvAdapter.HadithBookChaptersRvViewHolder>()  {

    class HadithBookChaptersRvViewHolder(val binding: HadithListRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var hadithBookChaptersItems: List<String> = chapters


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadithBookChaptersRvViewHolder {
        val binding =
            HadithListRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HadithBookChaptersRvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return hadithBookChaptersItems.size
    }

    override fun onBindViewHolder(holder: HadithBookChaptersRvViewHolder, position: Int) {
        holder.binding.apply {
            hadithFieldText.text = hadithBookChaptersItems[position]
        }

        holder.binding.root.setOnClickListener {
            listener.onItemclick(position + 1 )
        }

    }


}
interface OnHadithListRvListener {
    fun onItemclick(chapterNumber: Int)
}

package com.example.straterproject.ui.hadith.hadithBookChapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.straterproject.databinding.HadithListRvItemBinding

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
        val hadithNumber = position + 1
        holder.binding.apply {
            "الحديث رقم $hadithNumber".also { hadithName.text = it }
        }

        holder.binding.root.setOnClickListener {
            listener.onItemclick(hadithBookChaptersItems.get(position))
        }

    }


}
interface OnHadithListRvListener {
    fun onItemclick(hadithText: String)
}

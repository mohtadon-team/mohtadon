package com.mohtdon.ui.hadith.hadithCategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.mohtdon.databinding.HadithListRvItemBinding

class HadithCategoryRvAdapter(
    private val listener: OnHadithCategoryListener,
    private val hadithCategoryItems: ArrayList<String>
) : RecyclerView.Adapter<HadithCategoryRvAdapter.HadithCategoryRvViewHolder>() {

    class HadithCategoryRvViewHolder(val binding: HadithListRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var hadithCategoryListItems: List<String> = hadithCategoryItems


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadithCategoryRvViewHolder {
        val binding =
            HadithListRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HadithCategoryRvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return hadithCategoryListItems.size
    }

    override fun onBindViewHolder(holder: HadithCategoryRvViewHolder, position: Int) {
        holder.binding.apply {
            hadithFieldText.text = hadithCategoryListItems.get(position)
        }

        holder.binding.root.setOnClickListener {
            listener.onHadithCategoryitemClick(position)
        }

    }


}


interface OnHadithCategoryListener {
    fun onHadithCategoryitemClick(position: Int)
}

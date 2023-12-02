package com.example.straterproject.ui.hadith.hadithList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.straterproject.databinding.HadithListRvItemBinding

class HadithListRvAdapter(
    private val listener: OnHadithListRvListener,
    private val hadithItems:ArrayList<String>
    ): RecyclerView.Adapter<HadithListRvAdapter.hadithListRvViewHolder>()  {

    class hadithListRvViewHolder(val binding: HadithListRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var hadithListItems: List<String> = hadithItems


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): hadithListRvViewHolder {
        val binding =
            HadithListRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return hadithListRvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return hadithListItems.size
    }

    override fun onBindViewHolder(holder: hadithListRvViewHolder, position: Int) {
        val hadithNumber = position + 1
        holder.binding.apply {
            "الحديث رقم $hadithNumber".also { hadithName.text = it }
        }

        holder.binding.root.setOnClickListener {
            listener.onItemclick(hadithListItems.get(position))
        }

    }


}
interface OnHadithListRvListener {
    fun onItemclick(hadithText: String)
}

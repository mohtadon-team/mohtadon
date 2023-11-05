package com.example.straterproject.ui.hadith.hadithList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.straterproject.databinding.DuaaListRvItemBinding
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
        holder.binding.apply {
            hadithName.text = hadithListItems.get(position)
        }

        holder.binding.root.setOnClickListener {
            listener.onItemclick(position)
        }

    }


}
interface OnHadithListRvListener {
    fun onItemclick(position: Int)
}

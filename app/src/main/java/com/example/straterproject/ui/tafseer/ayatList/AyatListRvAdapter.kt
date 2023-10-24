package com.example.straterproject.ui.tafseer.ayatList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.straterproject.databinding.AyatListRvItemBinding
import com.example.straterproject.databinding.DuaaListRvItemBinding

class AyatListRvAdapter(
    private val listener: OnAyaListRvListener,
    private val ayatItems:ArrayList<String>
    ): RecyclerView.Adapter<AyatListRvAdapter.AyatListRvViewHolder>()  {

    class AyatListRvViewHolder(val binding: AyatListRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    var ayaListItems: List<String> = ayatItems


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatListRvViewHolder {
        val binding =
            AyatListRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AyatListRvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ayaListItems.size
    }

    override fun onBindViewHolder(holder: AyatListRvViewHolder, position: Int) {
        holder.binding.apply {
            ayaName.text = ayaListItems.get(position)
        }

        holder.binding.root.setOnClickListener {
            listener.onItemclick(position)
        }

    }


}
interface OnAyaListRvListener {
    fun onItemclick(position: Int)
}

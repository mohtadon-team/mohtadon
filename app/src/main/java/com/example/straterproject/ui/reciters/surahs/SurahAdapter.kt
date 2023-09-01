 package com.example.straterproject.ui.reciters.surahs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.reciters.SuraEntity
import com.example.straterproject.databinding.SurahItemToPlayBinding


class SurahAdapter (
    private val listener: OnSurahListener
) : RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    class SurahViewHolder(val binding: SurahItemToPlayBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<SuraEntity>() {
        override fun areItemsTheSame(oldItem: SuraEntity, newItem: SuraEntity): Boolean {
            return oldItem.originalNumber == newItem.originalNumber

        }

        override fun areContentsTheSame(oldItem: SuraEntity, newItem: SuraEntity): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var suras: List<SuraEntity?>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val binding = SurahItemToPlayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SurahViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        holder.binding.surahName.text = suras[position]!!.name
        holder.binding.surahNumber.text = suras[position]!!.originalNumber.toInt().toString()
        holder.binding.surahName.setOnClickListener {
            listener.onSurahClick( position)
        }
    }
    override fun getItemCount(): Int = suras.size
}



interface OnSurahListener {
    fun onSurahClick(position:Int)
}


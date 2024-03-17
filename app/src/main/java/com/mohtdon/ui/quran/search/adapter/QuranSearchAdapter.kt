package com.mohtdon.ui.quran.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mohtdon.domain.models.quran.Aya
import com.mohtdon.mohtdon.databinding.QuranSearchItemBinding

class QuranSearchAdapter(var listener: IoClickSearchItem) :
    RecyclerView.Adapter<QuranSearchAdapter.QuranSearchHolder>() {
    var searchList = ArrayList<Aya>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuranSearchHolder {
        val binding =
            QuranSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuranSearchHolder(binding)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: QuranSearchHolder, position: Int) {
        holder.bind(searchList.get(position))
        holder.itemView.setOnClickListener {
            listener.onSearchItemClick(searchList.get(position))
        }
    }

    fun updateListSearchResult(listSearch: ArrayList<Aya>) {
        searchList = listSearch
        notifyDataSetChanged()
    }

    class QuranSearchHolder(val binding: QuranSearchItemBinding) : ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(aya: Aya) {
            binding.apply {
                soraName.text = aya.sora_name_ar
                ayaNo.text ="ايه"+" "+aya.aya_no.toString()
                ayaContent.text = aya.aya_text
            }

        }

    }
}

interface IoClickSearchItem {
    fun onSearchItemClick(aya: Aya)
}
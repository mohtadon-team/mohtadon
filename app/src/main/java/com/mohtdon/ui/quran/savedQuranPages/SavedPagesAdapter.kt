package com.mohtdon.ui.quran.savedQuranPages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mohtdon.data.dataSource.remote.response.quran.models.SavedPageModel
import com.mohtdon.mohtdon.databinding.SavedPageItemBinding

class SavedPagesAdapter(
    private val savedPagesList: List<SavedPageModel>,
    private val savedSuraNamesList: List<String>, val listener: onSaveItemClickListener
) :
    RecyclerView.Adapter<SavedPagesAdapter.SavedPagesViewHolder>() {

    class SavedPagesViewHolder(val binding: SavedPageItemBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedPagesViewHolder {
        val binding =
            SavedPageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedPagesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return savedPagesList.size
    }

    override fun onBindViewHolder(holder: SavedPagesViewHolder, position: Int) {
        val pageNumber = savedPagesList.get(position).pageNumber
        val suraName = savedSuraNamesList.get(position)

        holder.binding.pageNum.text = pageNumber.toString()
        holder.binding.suraName.text = suraName

        holder.itemView.setOnClickListener {
            listener.onItemClick(pageNumber)
        }
    }

}

interface onSaveItemClickListener {
    fun onItemClick(pageNumber: Int)
}
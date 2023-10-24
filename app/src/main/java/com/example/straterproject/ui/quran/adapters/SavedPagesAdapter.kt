package com.example.straterproject.ui.quran.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.data.dataSource.remote.response.quran.models.SavedPageModel
import com.example.straterproject.databinding.SavedPageItemBinding

class SavedPagesAdapter(private val savedList: List<SavedPageModel>) :
    RecyclerView.Adapter<SavedPagesAdapter.savedPagesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): savedPagesHolder {
        val binding = SavedPageItemBinding.inflate(LayoutInflater.from(parent.context), null, false)
        return savedPagesHolder(binding)
    }

    override fun getItemCount(): Int {
        return savedList.size
    }

    override fun onBindViewHolder(holder: savedPagesHolder, position: Int) {
        holder.bind(savedList.get(position))
    }

    class savedPagesHolder(val binding: SavedPageItemBinding) : ViewHolder(binding.root) {

        fun bind(pageModel: SavedPageModel) {
            binding.pageNum.text = pageModel.pageNumber.toString()
        }

    }
}
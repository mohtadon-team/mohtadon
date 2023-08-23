package com.example.straterproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.straterproject.R
import com.example.straterproject.databinding.SurahItemBinding

class QuranAdapter : RecyclerView.Adapter<QuranAdapter.QuranHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuranHolder {
        val binding: SurahItemBinding =
            SurahItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuranHolder(binding)
    }

    override fun onBindViewHolder(holder: QuranHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    class QuranHolder(binding: SurahItemBinding) : RecyclerView.ViewHolder(binding.root)
}
package com.example.straterproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.reciters.MoshafEnitity

import com.example.straterproject.databinding.MoshafItemBinding



class MoshafAdapter constructor(
    private var listener: OnMoshafListener
) : RecyclerView.Adapter<MoshafAdapter.MoshafViewHolder>() {

    class MoshafViewHolder(val binding: MoshafItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<MoshafEnitity>() {
        override fun areItemsTheSame(oldItem: MoshafEnitity, newItem: MoshafEnitity): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: MoshafEnitity, newItem: MoshafEnitity): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }


    private val differ = AsyncListDiffer(this, diffCallback)

    var moshafs: List<MoshafEnitity>
        get() = differ.currentList
        set(value) = differ.submitList(value)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoshafViewHolder {
        val binding = MoshafItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoshafViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoshafViewHolder, position: Int) {
        val currentItem = moshafs[position]
        holder.binding.moshafName.text = currentItem.name
        holder.binding.root.setOnClickListener {
            listener.onMoshafClick(currentItem)
        }
    }
    override fun getItemCount(): Int = moshafs.size
}

interface OnMoshafListener {
    fun onMoshafClick(moshaf: MoshafEnitity)
}


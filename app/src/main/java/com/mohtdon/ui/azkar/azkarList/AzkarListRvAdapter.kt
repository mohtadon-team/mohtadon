package com.mohtdon.ui.azkar.azkarList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.mohtdon.databinding.AzkarListRvItemBinding

class AzkarListRvAdapter(
    private val listener: OnAzkarListRvListener, private val azkarItems: ArrayList<String>
) : RecyclerView.Adapter<AzkarListRvAdapter.AzkarListRvViewHolder>() {

    class AzkarListRvViewHolder(val binding: AzkarListRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    var azkarListItems: List<String> = azkarItems


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AzkarListRvViewHolder {
        val binding =
            AzkarListRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AzkarListRvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return azkarListItems.size
    }

    override fun onBindViewHolder(holder: AzkarListRvViewHolder, position: Int) {
        holder.binding.apply {
            zekrName.text = azkarListItems.get(position)
        }

        holder.binding.root.setOnClickListener {
            listener.onItemclick(position)
        }

    }


}

interface OnAzkarListRvListener {
    fun onItemclick(position: Int)
}

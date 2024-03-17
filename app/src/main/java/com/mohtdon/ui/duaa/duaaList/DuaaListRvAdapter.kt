package com.mohtdon.ui.duaa.duaaList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.mohtdon.databinding.DuaaListRvItemBinding

class DuaaListRvAdapter(
    private val listener: OnDuaaListRvListener,
    private val duaaItems:ArrayList<String>
    ): RecyclerView.Adapter<DuaaListRvAdapter.DuaaListRvViewHolder>()  {

    class DuaaListRvViewHolder(val binding: DuaaListRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var duaaListItems: List<String> = duaaItems


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DuaaListRvViewHolder {
        val binding =
            DuaaListRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DuaaListRvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return duaaListItems.size
    }

    override fun onBindViewHolder(holder: DuaaListRvViewHolder, position: Int) {
        holder.binding.apply {
            duaaName.text = duaaListItems.get(position)
        }

        holder.binding.root.setOnClickListener {
            listener.onItemclick(position)
        }

    }


}
interface OnDuaaListRvListener {
    fun onItemclick(position: Int)
}

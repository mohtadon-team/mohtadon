package com.mohtdon.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.mohtdon.databinding.ItemTodayThingBinding
import com.mohtdon.ui.home.rvitems.HomeTodayThingsRvItem

class HomeTodayThingsRvAdapter(
) : RecyclerView.Adapter<HomeTodayThingsRvAdapter.HomeTodayThingsRvViewHolder>() {

    class HomeTodayThingsRvViewHolder(val binding: ItemTodayThingBinding) :
        RecyclerView.ViewHolder(binding.root)


    private var HomeTodayThingsRvItems: List<HomeTodayThingsRvItem> = ArrayList<HomeTodayThingsRvItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTodayThingsRvViewHolder {
        val binding =
            ItemTodayThingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeTodayThingsRvViewHolder(binding)
    }


    override fun onBindViewHolder(holder: HomeTodayThingsRvViewHolder, position: Int) {
        holder.binding.apply {
            itemThingTvTitle.text = HomeTodayThingsRvItems.get(position).title
            itemThingTvContent.text = HomeTodayThingsRvItems.get(position).content
        }

    }

    override fun getItemCount(): Int = HomeTodayThingsRvItems.size

    fun setItems(items:MutableList<HomeTodayThingsRvItem>){
        HomeTodayThingsRvItems = items
    }

}


interface OnHomeTodayThingsRvListener {
    fun onItemclick(position: Int)
}

package com.mohtdon.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.HomeRvItemTemplateBinding
import com.mohtdon.ui.home.rvitems.HomeRvItem

class HomeRvAdapter(
    private val listener: OnHomeRvItemListener,
) : RecyclerView.Adapter<HomeRvAdapter.HomeRvViewHolder>() {

    class HomeRvViewHolder(val binding: HomeRvItemTemplateBinding) :
        RecyclerView.ViewHolder(binding.root)


    private var homeRvItems: List<HomeRvItem> = setHomeRvItems()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRvViewHolder {
        val binding =
            HomeRvItemTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeRvViewHolder(binding)
    }


    override fun onBindViewHolder(holder: HomeRvViewHolder, position: Int) {
        holder.binding.apply {
            holder.itemView.resources.getColorStateList(this@HomeRvAdapter.homeRvItems.get(position).itemBackground)
                .also { homeRvItemBackground.backgroundTintList = it }
            homeRvItemImage.setImageResource(homeRvItems.get(position).itemIcon)
            homeRvItemName.text =
                (holder.itemView.resources.getText(homeRvItems.get(position).itemName))

        }

        holder.binding.root.setOnClickListener {
            listener.onItemclick(position)
        }

    }

    override fun getItemCount(): Int = homeRvItems.size

    private fun setHomeRvItems(): ArrayList<HomeRvItem> {
        val array = ArrayList<HomeRvItem>()
//        array.add(
//            HomeRvItem( 1 ,
//                R.drawable.quran_icon, R.color.home_rv_item_color_1, R.string.home_rv_item_text_1
//            )
//        )
        array.add(
            HomeRvItem( 0 ,
                R.drawable.lantern , R.color.home_rv_item_color_2, R.string.home_rv_item_text_2
            )
        )
        array.add(
            HomeRvItem( 1 ,
                R.drawable.duaa, R.color.home_rv_item_color_3, R.string.home_rv_item_text_3
            )
        )
        array.add(
            HomeRvItem( 2 ,
                R.drawable.islamic_decoration, R.color.home_rv_item_color_4, R.string.home_rv_item_text_4
            )
        )
        array.add(
            HomeRvItem( 3 ,
                R.drawable.question_answer_1, R.color.home_rv_item_color_5, R.string.home_rv_item_text_5
            )
        )
        array.add(
            HomeRvItem( 4,
                R.drawable.right_arrow, R.color.home_rv_item_color_6, R.string.home_rv_item_text_6
            )
        )

        return array
    }

}


interface OnHomeRvItemListener {
    fun onItemclick(position: Int)
}

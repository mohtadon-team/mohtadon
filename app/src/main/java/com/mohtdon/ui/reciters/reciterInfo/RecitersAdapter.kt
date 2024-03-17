package com.mohtdon.ui.reciters.reciterInfo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.domain.models.reciters.MoshafEnitity
import com.mohtdon.domain.models.reciters.ReciterEntity
import com.mohtdon.mohtdon.databinding.MoshafItemBinding
import com.mohtdon.mohtdon.databinding.ReciterItemBinding


class RecitersAdapter(val context : Context ,val listener: OnMoshafListener) : RecyclerView.Adapter<RecitersAdapter.ReciterViewHolder>() {

    abstract class BaseViewHolder(binding : ViewDataBinding):RecyclerView.ViewHolder(binding.root)
    class ReciterViewHolder(val binding: ReciterItemBinding) : BaseViewHolder(binding)

    private val diffCallback = object : DiffUtil.ItemCallback<ReciterEntity>() {
        override fun areItemsTheSame(oldItem: ReciterEntity, newItem: ReciterEntity): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: ReciterEntity, newItem: ReciterEntity): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }


    private val differ = AsyncListDiffer(this, diffCallback)

    var reciters: List<ReciterEntity>
        get() = differ.currentList
        set(value) = differ.submitList(value)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReciterViewHolder {
        val binding = ReciterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReciterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReciterViewHolder, position: Int) {
        val currentItem = reciters[position]

         holder.binding.reciterName.text = currentItem.name

        val moshafAdapter = MoshafAdapter(listener)
        moshafAdapter.moshafs = currentItem.moshafEnitity
        holder.binding.childRv.layoutManager = LinearLayoutManager(context)
        holder.binding.childRv.setHasFixedSize(true)
        holder.binding.childRv.adapter = moshafAdapter



        holder.binding.root.setOnClickListener {
            currentItem.isExpandable = !currentItem.isExpandable
            notifyItemChanged(position)
        }
    }
    override fun getItemCount(): Int = reciters.size

}


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
        holder.binding.moshafName.text = currentItem.moshafName
        holder.binding.root.setOnClickListener {
            listener.onMoshafClick(currentItem)
        }
    }
    override fun getItemCount(): Int = moshafs.size
}

interface OnMoshafListener {
    fun onMoshafClick(moshaf: MoshafEnitity)
}




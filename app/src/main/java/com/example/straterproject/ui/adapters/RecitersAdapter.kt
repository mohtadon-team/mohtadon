package com.example.straterproject.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.dataSource.remote.response.reciters.Reciter
import com.example.domain.entity.reciters.MoshafEnitity
import com.example.domain.entity.reciters.ReciterEntity
import com.example.straterproject.R

import com.example.straterproject.databinding.ReciterItemBinding


class RecitersAdapter(val context : Context ,val listener: OnMoshafListener) : RecyclerView.Adapter<RecitersAdapter.ReciterViewHolder>() {

    class ReciterViewHolder(val binding: ReciterItemBinding) : RecyclerView.ViewHolder(binding.root)

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

        val isExpandable = currentItem.isExpandable
         holder.binding.reciterName.text = currentItem.name

        if (isExpandable) {
            holder.binding.expandableLayout.visibility = View.VISIBLE
            holder.binding.arroImageview.setImageResource(R.drawable.arrow_up)
        }else {
            holder.binding.expandableLayout.visibility = View.GONE
            holder.binding.arroImageview.setImageResource(R.drawable.arrow_down)
        }

        val moshafAdapter = MoshafAdapter(listener)
        moshafAdapter.moshafs = currentItem.moshafEnitity
        holder.binding.childRv.layoutManager = LinearLayoutManager(context)
        holder.binding.childRv.setHasFixedSize(true)
        holder.binding.childRv.adapter = moshafAdapter

        holder.binding.arroImageview.setOnClickListener {
            reciters[position].isExpandable = !isExpandable
            notifyItemChanged(position)
        }


    }
    override fun getItemCount(): Int = reciters.size


}


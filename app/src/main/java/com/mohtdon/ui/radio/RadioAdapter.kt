package com.mohtdon.ui.radio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.domain.models.radio.RadioEntity
import com.mohtdon.mohtdon.databinding.RadioItemBinding
import com.mohtdon.utilities.LastPlayedTrackPreference

class RadioAdapter (
    private val listener: OnRadioStationListener,
    private val lastPlayedTrackPreference: LastPlayedTrackPreference
) : RecyclerView.Adapter<RadioAdapter.RadioStationViewHolder>() {

    class RadioStationViewHolder(val binding: RadioItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<RadioEntity>() {
        override fun areItemsTheSame(oldItem: RadioEntity, newItem: RadioEntity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: RadioEntity, newItem: RadioEntity): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var radioStations: List<RadioEntity?>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioStationViewHolder {
        val binding = RadioItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RadioStationViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RadioStationViewHolder, position: Int) {
        holder.binding.apply {
            radioStationName.text = radioStations[position]!!.name
//            radioNumber.text = (position+1).toString()
        }
//
//         if (position == lastPlayedTrackPreference.lastPlayedTrackId.toInt()) {
//            holder.binding.play.visibility = View.GONE
//            holder.binding.pause.visibility = View.VISIBLE
//        }else {
//            holder.binding.play.visibility = View.VISIBLE
//            holder.binding.pause.visibility = View.GONE
//        }
//
        holder.binding.root.setOnClickListener {
//            lastPlayedTrackPreference.beforelastPlayedTrackId = lastPlayedTrackPreference.lastPlayedTrackId
//            notifyItemChanged(lastPlayedTrackPreference.beforelastPlayedTrackId.toInt())
            listener.onStationClick(position)
        }

    }
    override fun getItemCount(): Int = radioStations.size
}

interface OnRadioStationListener {
    fun onStationClick(position:Int)
}


package com.mohtdon.ui.azkar.azkarDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.mohtdon.databinding.AzkarDetailsRvItemBinding

class AzkarDetailsRvAdapter constructor(
    azkarItems: ArrayList<ZekrDetailsModel>
) : RecyclerView.Adapter<AzkarDetailsRvAdapter.AzkarDetailsRvViewHolder>() {

    class AzkarDetailsRvViewHolder(val binding: AzkarDetailsRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var azkarDetailsItems: List<ZekrDetailsModel> = azkarItems


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AzkarDetailsRvViewHolder {
        val binding =
            AzkarDetailsRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AzkarDetailsRvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return azkarDetailsItems.size
    }

    override fun onBindViewHolder(holder: AzkarDetailsRvViewHolder, position: Int) {
        holder.binding.apply {
            val zekrDetailsItem = azkarDetailsItems.get(position)
            zekrText.text = zekrDetailsItem.zekrText
            zekrNumberOfRepeats.text = zekrDetailsItem.zekrNumberOfRepeation.toString()
        }
    }
}

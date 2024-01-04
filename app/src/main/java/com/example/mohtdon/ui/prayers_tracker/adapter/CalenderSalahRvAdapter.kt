package com.example.mohtdon.ui.prayers_tracker.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mohtdon.R
import com.example.mohtdon.databinding.TrackerPrayersRvItemBinding
import com.example.mohtdon.ui.prayers_tracker.models.Salah


class CalenderSalahRvAdapter(
    val Prayers: List<Salah>, val listener: OnPrayersCheckedItemListener
) : RecyclerView.Adapter<CalenderSalahRvAdapter.CalenderSalahViewHolder>() {


    class CalenderSalahViewHolder(val binding: TrackerPrayersRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    private var WeekCalenderItemsSalahItems = Prayers




    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CalenderSalahViewHolder {
        val binding =
            TrackerPrayersRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalenderSalahViewHolder(binding)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CalenderSalahViewHolder, position: Int) {
        val salah = WeekCalenderItemsSalahItems[position]

        holder.binding.apply {
            if (!salah.isItToday) {
                salahChecked.visibility = View.GONE
                salahName.visibility = View.VISIBLE
                salahName.text = salah.name
                view.setBackgroundColor(Color.WHITE)
            } else {
                salahChecked.text = salah.name
                if (salah.isPerformed) {
                    view.setBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context, R.color.salah_tracker_salah_performed_color
                        )
                    )
                    salahChecked.isChecked = true
                } else {
                    view.setBackgroundColor(Color.WHITE)
                    salahChecked.isChecked = false
                }
            }
            salahTime.text = salah.time
        }

        holder.binding.salahChecked.setOnCheckedChangeListener { compoundButton, b ->

            if (salah.isItToday) {
                when (salah.name) {
                    com.example.mohtdon.utilities.FAJR -> listener.onItemClick(com.example.mohtdon.utilities.FAJR, b)
                    com.example.mohtdon.utilities.DHUHR -> listener.onItemClick(com.example.mohtdon.utilities.DHUHR, b)
                    com.example.mohtdon.utilities.ASR -> listener.onItemClick(com.example.mohtdon.utilities.ASR, b)
                    com.example.mohtdon.utilities.MAGHRIB -> listener.onItemClick(com.example.mohtdon.utilities.MAGHRIB, b)
                    com.example.mohtdon.utilities.ISHA -> listener.onItemClick(com.example.mohtdon.utilities.ISHA, b)
                }

                if (b) {
                    holder.binding.view.setBackgroundColor(
                        androidx.core.content.ContextCompat.getColor(
                            holder.itemView.context, com.example.mohtdon.R.color.salah_tracker_salah_performed_color
                        )
                    )
                } else {
                    holder.binding.view.setBackgroundColor(android.graphics.Color.WHITE)
                }
            }

        }

        }

    override fun getItemCount(): Int = WeekCalenderItemsSalahItems.size


    interface OnPrayersCheckedItemListener {
        fun onItemClick(salahName: String, value: Boolean)
    }
}

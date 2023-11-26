package com.example.straterproject.ui.prayers_tracker

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.straterproject.R
import com.example.straterproject.databinding.TrackerPrayersRvItemBinding
import com.example.straterproject.utilities.ASR
import com.example.straterproject.utilities.DHUHR
import com.example.straterproject.utilities.FAJR
import com.example.straterproject.utilities.ISHA
import com.example.straterproject.utilities.MAGHRIB


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

        holder.binding.root.setOnClickListener {
            if (salah.isItToday) {

                val isChecked = holder.binding.salahChecked.isChecked.not()
                when (salah.name) {
                    FAJR -> listener.onItemClick(FAJR, isChecked)
                    DHUHR -> listener.onItemClick(DHUHR, isChecked)
                    ASR -> listener.onItemClick(ASR, isChecked)
                    MAGHRIB -> listener.onItemClick(MAGHRIB, isChecked)
                    ISHA -> listener.onItemClick(ISHA, isChecked)
                }

                if (isChecked) {

                    Log.i("ahmed", isChecked.toString())
                    holder.binding.view.setBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context, R.color.salah_tracker_salah_performed_color
                        )
                    )
                } else {
                    Log.i("ahmed", isChecked.toString())
                    holder.binding.view.setBackgroundColor(Color.WHITE)
                }
            }

        }


    }

    override fun getItemCount(): Int = WeekCalenderItemsSalahItems.size


    interface OnPrayersCheckedItemListener {
        fun onItemClick(salahName: String, value: Boolean)
    }
}

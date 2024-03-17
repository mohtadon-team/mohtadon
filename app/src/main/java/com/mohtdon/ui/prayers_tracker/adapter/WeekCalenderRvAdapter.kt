package com.mohtdon.ui.prayers_tracker.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.WeeklyCalenderRvItemBinding
import com.mohtdon.ui.prayers_tracker.TrackerViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

class WeekCalenderRvAdapter(
    val days: ArrayList<LocalDate>, val listener: OnWeeklyCalenderListener
) : RecyclerView.Adapter<WeekCalenderRvAdapter.WeekCalenderViewHolder>() {

    class WeekCalenderViewHolder(val binding: WeeklyCalenderRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    private var WeekCalenderItems = days

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekCalenderViewHolder {
        val binding =
            WeeklyCalenderRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeekCalenderViewHolder(binding)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: WeekCalenderViewHolder, position: Int) {
        val date = WeekCalenderItems[position]

        holder.binding.apply {
            if (date == null) {
                weeklyCalenderDayName.text = ""
            } else {
                weeklyCalenderDayName.text = date.format(
                    DateTimeFormatter.ofLocalizedDate(
                        FormatStyle.FULL   // or LONG or MEDIUM or SHORT
                    ).withLocale(
                        Locale(
                            "ar", "eg"
                        )   // English language, with cultural norms of United States.
                    )
                ).toString().split(" ")[0].replace('،', ' ').replace("ال", " ").trim()


                weeklyCalenderDay.text = date.dayOfMonth.toString()

                val orange = ContextCompat.getColor(
                    holder.itemView.context, R.color.orange
                )
                if (date.equals(TrackerViewModel.selectedDate)) {
                    card.setCardBackgroundColor(
                        Color.WHITE
                    )
                    weeklyCalenderDay.setTextColor(orange)
                    weeklyCalenderDayName.setTextColor(orange)
                }
            }
        }

        holder.binding.root.setOnClickListener {
            listener.onItemClick(position, date)
        }

    }

    override fun getItemCount(): Int = WeekCalenderItems.size


}


interface OnWeeklyCalenderListener {
    fun onItemClick(position: Int, date: LocalDate?)
}

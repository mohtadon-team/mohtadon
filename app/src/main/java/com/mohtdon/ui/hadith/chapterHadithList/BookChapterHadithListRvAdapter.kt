package com.mohtdon.ui.hadith.chapterHadithList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.mohtdon.databinding.ItemHadithDetailsBinding

class BookChapterHadithListRvAdapter(
      hadithList:List<String>
    ): RecyclerView.Adapter<BookChapterHadithListRvAdapter.BookChapterHadithListRvViewHolder>()  {

    class BookChapterHadithListRvViewHolder(val binding: ItemHadithDetailsBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var hadithListItems: List<String> = hadithList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookChapterHadithListRvViewHolder {
        val binding =
            ItemHadithDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookChapterHadithListRvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return hadithListItems.size
    }

    override fun onBindViewHolder(holder: BookChapterHadithListRvViewHolder, position: Int) {
        holder.binding.apply {
            hadithText.text = hadithListItems[position]
        }



    }


}
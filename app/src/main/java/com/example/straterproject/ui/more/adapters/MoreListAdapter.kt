package com.example.straterproject.ui.more.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.straterproject.R
import com.example.straterproject.ui.more.MoreItemModel

class MoreListAdapter(
    private val listener: OnNameGridViewListener,
    private val context: FragmentActivity?,
    private val data: ArrayList<MoreItemModel>
) : BaseAdapter(),
    AdapterView.OnItemClickListener {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = p1 ?: inflater.inflate(R.layout.more_list_item, p2, false)
        val textView: TextView = view.findViewById(R.id.more_rv_item_name)
        val imageView: ImageView = view.findViewById(R.id.more_rv_item_image)
        textView.text = data[p0].name
        imageView.setImageResource(data[p0].image)

        return view
    }

    interface OnNameGridViewListener {
        fun onItemClick(position: Int)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        listener.onItemClick(p2)
    }
}
package com.mohtdon.ui.namesOfAllah.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.mohtdon.mohtdon.R

class NamesGridViewAdapter(private val listener: OnNameGridViewListener, private val context: FragmentActivity?, private val data: List<String>) : BaseAdapter(),
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
        val view = p1 ?: inflater.inflate(R.layout.names_of_allah_list_item, null, false)
        val textView: TextView = view.findViewById(R.id.name_item_text)
        textView.text = data[p0]
        return view
    }
    interface OnNameGridViewListener {
        fun onItemclick(position: Int)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        listener.onItemclick(p2)
    }
}
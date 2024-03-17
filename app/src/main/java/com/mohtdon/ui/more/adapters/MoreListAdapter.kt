package com.mohtdon.ui.more.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.mohtdon.mohtdon.R
import com.mohtdon.ui.more.MoreItemModel

class MoreListAdapter(
    private val listener: OnNameGridViewListener,
    private val context: FragmentActivity?,
    private val moreItemsList: ArrayList<MoreItemModel>
) : BaseAdapter(),
    AdapterView.OnItemClickListener {
    override fun getCount(): Int {
        return moreItemsList.size
    }

    override fun getItem(position: Int): Any {
        return moreItemsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position0: Int, position1: View?, position2: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = position1 ?: inflater.inflate(R.layout.more_list_item, null, false)
        val textView: TextView = view.findViewById(R.id.more_rv_item_name)
        val imageView: ImageView = view.findViewById(R.id.more_rv_item_image)
        textView.text = moreItemsList[position0].name
        imageView.setImageResource(moreItemsList[position0].image)

        return view
    }

    interface OnNameGridViewListener {
        fun onItemClick(position: Int)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        listener.onItemClick(p2)
    }
}
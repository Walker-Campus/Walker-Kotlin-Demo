package com.example.walkermusic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walkermusic.Information
import com.example.walkermusic.R

class MainListAdapter(private val list: List<Information>)
    : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    //继承view holder，然后传入列表中的组件进入view holder
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.main_list_image_view)
        val text: TextView = view.findViewById(R.id.main_list_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.main_recyclerview_layout, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = list[position]
        holder.run {
            image.setImageResource(fruit.imageid)
            text.text = fruit.text
        }
    }

}
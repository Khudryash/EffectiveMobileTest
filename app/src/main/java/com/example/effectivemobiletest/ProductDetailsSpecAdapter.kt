package com.example.effectivemobiletest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductDetailsSpecAdapter(private val context: Context, private val titleList: List<String>):
    RecyclerView.Adapter<ProductDetailsSpecAdapter.PageHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
            PageHolder(
                LayoutInflater.from(context).inflate(R.layout.activity_producr_details, parent, false)
            )

        override fun onBindViewHolder(holder: PageHolder, position: Int) {
            holder.textView.text = titleList[position]
        }

        override fun getItemCount(): Int = titleList.size

        inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById<TextView>(R.id.title)
        }
}
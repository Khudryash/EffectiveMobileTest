package com.example.effectivemobiletest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_store_item.view.*


class HomeStoreAdapter(private val context: Context, private val bannerList: List<HomeStore>?): RecyclerView.Adapter<HomeStoreAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var subtitle: TextView
        var isNewMark: ImageView
        var isBuy: TextView
        var bannerImage: ImageView

        init {
            title = itemView.title
            subtitle = itemView.subtitle
            isNewMark = itemView.isNewMark
            isBuy = itemView.isBuy
            bannerImage = itemView.bannerImage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.home_store_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load(bannerList!![position].picture)
            .into(holder.bannerImage)
        holder.title.text = bannerList[position].title
        holder.subtitle.text = bannerList[position].subtitle
        if (!bannerList[position].is_new){

            holder.isNewMark.visibility = View.INVISIBLE
        }
        else {

            holder.isNewMark.visibility = View.VISIBLE
        }
        if (!bannerList[position].is_buy){

            holder.isBuy.visibility = View.INVISIBLE
        }


    }

    override fun getItemCount(): Int {
        return bannerList?.size!!
    }
}
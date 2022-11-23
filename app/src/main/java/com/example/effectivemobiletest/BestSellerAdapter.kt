package com.example.effectivemobiletest

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.best_seller_item.view.*

import android.content.Intent

class BestSellerAdapter(val context: Context, val bannerList: List<BestSeller>?): RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


        var title: TextView
        var discountPrice: TextView
        var priceWithoutDiscount: TextView
        var picture: ImageView
        var favorite: ImageView

        init {
            title = itemView.title
            discountPrice = itemView.discountPrice
            priceWithoutDiscount = itemView.priceWithoutDiscount
            picture = itemView.picture
            favorite = itemView.isFavorite

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.best_seller_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load(bannerList!![position].picture)
            .into(holder.picture)
        if(bannerList[position].is_favorites) {
            holder.favorite.setImageResource(R.drawable.ic_favorite_fill)
            holder.favorite.contentDescription = "favorite mark"
        } else {
            holder.favorite.setImageResource(R.drawable.ic_favorite_empty)
            holder.favorite.contentDescription = "not favorite mark"
        }
        holder.title.text = bannerList[position].title
        holder.discountPrice.text = "$"+bannerList[position].price_without_discount.toString()
        holder.priceWithoutDiscount.apply {
            paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            text = "$"+bannerList[position].discount_price.toString()
        }
        val favoriteClick = holder.favorite
        favoriteClick.setOnClickListener{
            if (favoriteClick.contentDescription == "favorite mark"){
                favoriteClick.setImageResource(R.drawable.ic_favorite_empty)
                holder.favorite.contentDescription = "not favorite mark"
            } else {
                holder.favorite.setImageResource(R.drawable.ic_favorite_fill)
                holder.favorite.contentDescription = "favorite mark"
            }
            }

        holder.itemView.setOnClickListener{
            context.startActivity(Intent(
                this.context,
                ProducrDetails::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
        holder.picture.setOnClickListener{
            context.startActivity(Intent(
                this.context,
                ProducrDetails::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }


    }

    override fun getItemCount(): Int {
        return bannerList?.size!!
    }
}
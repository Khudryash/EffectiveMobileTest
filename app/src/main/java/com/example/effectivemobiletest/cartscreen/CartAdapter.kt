package com.example.effectivemobiletest.cartscreen

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.productdetailsscreen.ProductDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_producr_details.view.*
import kotlinx.android.synthetic.main.cart_item.*
import kotlinx.android.synthetic.main.cart_item.view.*

class CartAdapter(private val context: Context, private val basketList: List<Basket>?): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var price: TextView
        var picture: ImageView
        var plus: TextView
        var minus: TextView
        val count: TextView
        var bin: ImageButton
        var card: ConstraintLayout

        init {
            title = itemView.itemTitle
            price = itemView.itemPrice
            picture = itemView.itemPicture
            plus = itemView.countPlus
            minus = itemView.countMinus
            count = itemView.counter
            bin = itemView.bin
            card = itemView.cartCard
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false)
        return CartAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        Picasso.get()
            .load(basketList!![position].images)
            .into(holder.picture)
        holder.title.text = basketList[position].title
        holder.price.text = "$"+basketList[position].price.toString()
        holder.plus.setOnClickListener{
            holder.count.text = (holder.count.text.toString().toInt() + 1).toString()
        }
        holder.minus.setOnClickListener{
            if (holder.count.text.toString().toInt() > 0){
                holder.count.text = (holder.count.text.toString().toInt() - 1).toString()
            }
        }
        holder.bin.setOnClickListener{
            holder.count.text = "0"
        }

        holder.card.setOnClickListener{
            context.startActivity(Intent(context, ProductDetails::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }

    }

    override fun getItemCount(): Int {
        return basketList?.size!!
    }

}
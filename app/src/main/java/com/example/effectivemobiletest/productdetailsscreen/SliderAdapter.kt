package com.example.effectivemobiletest.productdetailsscreen

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.effectivemobiletest.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.slide_item_container.view.*

class SliderAdapter() : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>(), Parcelable {

    private lateinit var sliderList: List<SliderItem>
    private lateinit var viewPager2: ViewPager2

    constructor(parcel: Parcel) : this() {

    }

    constructor(sliderList: List<SliderItem>, viewPager2: ViewPager2) : this() {
        this.sliderList = sliderList
        this.viewPager2 = viewPager2
    }

    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setImage(sliderItem: SliderItem) {
            Picasso.get()
                .load(sliderItem.getImage())
                .into(itemView.imageSlide)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(sliderList[position])
    }

    override fun getItemCount(): Int {
        return sliderList.size
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SliderAdapter> {
        override fun createFromParcel(parcel: Parcel): SliderAdapter {
            return SliderAdapter(parcel)
        }

        override fun newArray(size: Int): Array<SliderAdapter?> {
            return arrayOfNulls(size)
        }
    }

}
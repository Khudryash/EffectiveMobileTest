package com.example.effectivemobiletest.productdetailsscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.cartscreen.Cart
import com.example.effectivemobiletest.data.SharedViewModel
import kotlinx.android.synthetic.main.activity_producr_details.*

class ProductDetails : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy{
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producr_details)

        back_button.setOnClickListener{
            finish()
        }

        cart_button.setOnClickListener{
            startActivity(Intent(this, Cart::class.java))
        }

        viewModel.refreshProduct()
        viewModel.ProductDataLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            modelTitle.text = response.title
            rating.rating = response.rating.toFloat()
            price.text = "$" + response.price

            if (response.isFavorites) {
                favorite.setBackgroundResource(R.drawable.orange_button_background)
                favorite.contentDescription = "is favorite mark"
            } else {
                favorite.setBackgroundResource(R.drawable.dark_blue_button_background)
                favorite.contentDescription = "is not favorite mark"
            }
            favorite.setOnClickListener() {
                if (favorite.contentDescription == "is favorite mark"){
                    favorite.setBackgroundResource(R.drawable.dark_blue_button_background)
                    favorite.contentDescription = "is not favorite mark"
                } else {
                    favorite.setBackgroundResource(R.drawable.orange_button_background)
                    favorite.contentDescription = "is favorite mark"
                }
            }
            val prodAdapter = ProductDetailsSpecAdapter(supportFragmentManager)
            prodAdapter.addFragment(ProductSpecsFragment(listOf(response.CPU, response.camera, response.ssd, response.sd)), "Shop")
            prodAdapter.addFragment(ProductInfoFragment(), "Details")
            prodAdapter.addFragment(ProductFeaturesFragment(), "Features")

            tabLayout.setupWithViewPager(specViewPager)
            specViewPager.adapter = prodAdapter

            val sliderList: List<SliderItem> = listOf(SliderItem(response.images[0]), SliderItem(response.images[1]))

            imageSlider.adapter = SliderAdapter(sliderList, imageSlider)

            imageSlider.clipToPadding = false
            imageSlider.clipChildren = false
            imageSlider.offscreenPageLimit = 3
            imageSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(40))
            compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
                val r = 1-Math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            })

            imageSlider.setPageTransformer(compositePageTransformer)

        }


    }
}
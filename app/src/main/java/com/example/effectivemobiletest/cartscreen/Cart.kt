package com.example.effectivemobiletest.cartscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.data.SharedViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_producr_details.*
import kotlinx.android.synthetic.main.cart_item.*
import kotlinx.android.synthetic.main.activity_producr_details.back_button as back_button1

class Cart : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy{
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    lateinit var cartAdapter: CartAdapter
    lateinit var cartLinearLayoutManager: LinearLayoutManager
    lateinit var total: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        back_button.setOnClickListener{
            finish()
        }

        viewModel.refreshCart()
        viewModel.CartDataLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            delivery.text = response.delivery
            totalPrice.text = "$" + response.total.toString() + " usd"
            total = totalPrice

            cartLinearLayoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
            basketRecycler.layoutManager = cartLinearLayoutManager
            cartAdapter = CartAdapter(baseContext, response.basket)
            cartAdapter.notifyDataSetChanged()
            basketRecycler.adapter = cartAdapter
        }
    }

}
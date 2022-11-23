package com.example.effectivemobiletest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_producr_details.*
import kotlinx.android.synthetic.main.product_details_shop.*

class ProducrDetails : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy{
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producr_details)

        back_button.setOnClickListener{
            finish()
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
//            cpuSpec.text = response.CPU
//            camSpec.text = response.camera
//            ramSpec.text = response.ssd
//            ssdSpec.text = response.sd
        }
    }
}
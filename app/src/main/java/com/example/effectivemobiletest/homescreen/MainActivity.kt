package com.example.effectivemobiletest.homescreen


import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletest.data.SharedViewModel
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.filter_layout.view.*


class MainActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy{
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    lateinit var homeStoreAdapter: HomeStoreAdapter
    lateinit var bestSellerAdapter: BestSellerAdapter
    lateinit var homeStoreLinearLayoutManager: LinearLayoutManager
    lateinit var bestSellerGridLayoutManager: GridLayoutManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        filterButton.setOnClickListener{
             val bottomSheetDialog = BottomSheetDialog(
                 this,
                 R.style.FilterDialogTheme
             )
            val filterView = LayoutInflater.from(this)
                .inflate(
                    R.layout.filter_layout,
                    findViewById(R.id.filerLayout)
                )
            bottomSheetDialog.setContentView(filterView)
            bottomSheetDialog.show()

            val brands = resources.getStringArray(R.array.brands)
            val brandsAdapter = ArrayAdapter(this, R.layout.dropdown_item, brands)
            filterView.brandsAutoCompleteTextView.setAdapter(brandsAdapter)

            val price = resources.getStringArray(R.array.price)
            val priceAdapter = ArrayAdapter(this, R.layout.dropdown_item, price)
            filterView.priceAutoCompleteTextView.setAdapter(priceAdapter)

            val size = resources.getStringArray(R.array.size)
            val sizeAdapter = ArrayAdapter(this, R.layout.dropdown_item, size)
            filterView.sizeAutoCompleteTextView.setAdapter(sizeAdapter)

            filterView.cancel_button.setOnClickListener{
                bottomSheetDialog.hide()
            }
            filterView.done_button.setOnClickListener{
                bottomSheetDialog.hide()
            }
        }


        homeStore_recycler.setHasFixedSize(true)
        homeStoreLinearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        homeStore_recycler.layoutManager = homeStoreLinearLayoutManager

        bestSellerGridLayoutManager = GridLayoutManager(
            this,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        bestSeller_recycler.layoutManager = bestSellerGridLayoutManager


         getHomeStore()

    }

    private fun getHomeStore() {

        viewModel.refreshHome()
        viewModel.homeDataLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            homeStoreAdapter = HomeStoreAdapter(baseContext, response.home_store)
            homeStoreAdapter.notifyDataSetChanged()
            homeStore_recycler.adapter = homeStoreAdapter

            bestSellerAdapter = BestSellerAdapter(baseContext, response.best_seller)
            bestSellerAdapter.notifyDataSetChanged()
            bestSeller_recycler.adapter = bestSellerAdapter
        }
    }
}
package com.example.effectivemobiletest

import android.os.Bundle
import android.util.Log.d
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletest.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy{
        ViewModelProvider(this).get(SharedViewModel::class.java)
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

        homeStore_recycler.setHasFixedSize(true)
        homeStoreLinearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        homeStore_recycler.layoutManager = homeStoreLinearLayoutManager

//        bestSeller_recycler.setHasFixedSize(true)
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
//        val favoriteClick = findViewById<ImageView>(R.id.isFavorite)
//        favoriteClick.setOnClickListener{
//            favoriteClick.setImageResource(R.drawable.favorite_empty)}
    }
}
package com.example.effectivemobiletest

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletest.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var homeStoreAdapter: HomeStoreAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeStore_recycler.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        homeStore_recycler.layoutManager = linearLayoutManager

        getHomeStore()
    }

    private fun getHomeStore() {
        val retrofitBulder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://run.mocky.io/v3/")
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBulder.getHomeStore()

        retrofitData.enqueue(object : Callback<HomeData> {
            override fun onResponse(
                call: Call<HomeData>,
                response: Response<HomeData>
            ) {
                val responseBody = response.body()?.home_store

                homeStoreAdapter = HomeStoreAdapter(baseContext, responseBody)
                homeStoreAdapter.notifyDataSetChanged()
                homeStore_recycler.adapter = homeStoreAdapter

            }

            override fun onFailure(call: Call<HomeData>, t: Throwable) {
                d("MainActivity", "onFailureRequest "+t.message + "\n" + call.toString())
            }
        })
    }
}
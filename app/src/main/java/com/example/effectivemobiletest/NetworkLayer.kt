package com.example.effectivemobiletest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkLayer {

    val retrofitBulder: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://run.mocky.io/v3/")
        .build()

    val apiInterface: ApiInterface by lazy {
        retrofitBulder.create(ApiInterface::class.java)
    }

    val apiClient = ApiClient(apiInterface)

}
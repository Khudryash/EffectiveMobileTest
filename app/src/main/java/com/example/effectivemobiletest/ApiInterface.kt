package com.example.effectivemobiletest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {

    @GET(value = "654bd15e-b121-49ba-a588-960956b15175")
    fun getHomeStore(): Call<HomeData>

}
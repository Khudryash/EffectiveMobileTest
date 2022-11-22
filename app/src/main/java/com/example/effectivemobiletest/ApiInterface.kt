package com.example.effectivemobiletest

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET(value = "654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getHomeStore(): Response<HomeDataResponse>

}
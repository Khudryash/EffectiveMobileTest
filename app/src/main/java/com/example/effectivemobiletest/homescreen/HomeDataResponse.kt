package com.example.effectivemobiletest.homescreen

data class HomeDataResponse(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)
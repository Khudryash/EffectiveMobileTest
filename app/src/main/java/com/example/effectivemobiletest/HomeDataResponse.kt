package com.example.effectivemobiletest

data class HomeDataResponse(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)
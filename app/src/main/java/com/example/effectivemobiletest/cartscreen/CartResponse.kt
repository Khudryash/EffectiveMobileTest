package com.example.effectivemobiletest.cartscreen

data class CartResponse(
    val basket: List<Basket>,
    val delivery: String,
    val id: String,
    val total: Int
)
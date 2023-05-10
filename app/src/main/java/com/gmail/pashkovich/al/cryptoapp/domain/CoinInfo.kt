package com.gmail.pashkovich.al.cryptoapp.domain


data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String?,
    val price: Double?,
    val lastUpdate: String,
    val lastMarket: String?,
    val highDay: Double?,
    val lowDay: Double?,
    val imageUrl: String
)
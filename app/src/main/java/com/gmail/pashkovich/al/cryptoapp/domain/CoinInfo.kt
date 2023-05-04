package com.gmail.pashkovich.al.cryptoapp.domain


data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String? = null,
    val price: Double? = null,
    val lastUpdate: Long? = null,
    val highDay: Double? = null,
    val lowDay: Double? = null,
    val imageUrl: String? = null
)
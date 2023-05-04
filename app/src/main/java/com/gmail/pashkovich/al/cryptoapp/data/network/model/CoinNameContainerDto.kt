package com.gmail.pashkovich.al.cryptoapp.data.network.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




 data class CoinNameContainerDto (
    @SerializedName("CoinName")
    @Expose
    val coinName: CoinNameDto? = null
 )
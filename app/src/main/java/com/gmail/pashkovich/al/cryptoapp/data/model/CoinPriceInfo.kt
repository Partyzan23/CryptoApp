package com.gmail.pashkovich.al.cryptoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.pashkovich.al.cryptoapp.data.network.ApiFactory.BASE_IMAGE_URL
import com.gmail.pashkovich.al.cryptoapp.utils.convertTimestampToTime
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



@Entity(tableName = "full_price_list")
data class CoinPriceInfo (
    @SerializedName("TYPE")
    @Expose
    val type: String? = null,
    @SerializedName("MARKET")
    @Expose
    val market: String? = null,
    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String,
    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String? = null,
    @SerializedName("FLAGS")
    @Expose
    val flags: String? = null,
    @SerializedName("PRICE")
    @Expose
    val price: Double? = null,
    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Long? = null,
    @SerializedName("LASTVOLUME")
    @Expose
    val lastVolume: Double? = null,
    @SerializedName("LASTVOLUMETO")
    @Expose
    val lastVolumeTo: Double? = null,
    @SerializedName("LASTTRADEID")
    @Expose
    val lastTradeId: String? = null,
    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeDay: Double? = null,
    @SerializedName("VOLUMEDAYTO")
    @Expose
    val volumeDayTo: Double? = null,
    @SerializedName("VOLUME24HOUR")
    @Expose
    val volume24Hour: Double? = null,
    @SerializedName("VOLUME24HOURTO")
    @Expose
    val volume24HourTo: Double? = null,
    @SerializedName("OPENDAY")
    @Expose
    val openDay: Double? = null,
    @SerializedName("HIGHDAY")
    @Expose
    val highDay: Double? = null,
    @SerializedName("LOWDAY")
    @Expose
    val lowDay: Double? = null,
    @SerializedName("OPEN24HOUR")
    @Expose
    val open24Hour: Double? = null,
    @SerializedName("HIGH24HOUR")
    @Expose
    val high24Hour: Double? = null,
    @SerializedName("LOW24HOUR")
    @Expose
    val low24Hour: Double? = null,
    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String? = null,
    @SerializedName("CHANGEDAY")
    @Expose
    val changeDay: Double? = null,
    @SerializedName("TOTALVOLUME24H")
    @Expose
    val totalVolume24h: Double? = null,
    @SerializedName("TOTALVOLUME24HTO")
    @Expose
    val totalVolume24hTo: Double? = null,
    @SerializedName("TOTALTOPTIERVOLUME24H")
    @Expose
    val totalTopTierVolume24h: Double? = null,
    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    @Expose
    val totalTopTierVolume24hTo: Double? = null,
    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String? = null
) {
    fun getFormattedTime() : String {
        return convertTimestampToTime(lastUpdate)
    }

    fun getFullImageUrl(): String {
        return BASE_IMAGE_URL + imageUrl
    }
}
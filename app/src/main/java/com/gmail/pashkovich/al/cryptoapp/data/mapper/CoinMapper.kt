package com.gmail.pashkovich.al.cryptoapp.data.mapper

import com.gmail.pashkovich.al.cryptoapp.data.database.CoinInfoDbModel
import com.gmail.pashkovich.al.cryptoapp.data.network.model.CoinInfoDto
import com.gmail.pashkovich.al.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.gmail.pashkovich.al.cryptoapp.data.network.model.CoinNamesListDto
import com.gmail.pashkovich.al.cryptoapp.domain.CoinInfo
import com.google.gson.Gson
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CoinMapper @Inject constructor(){

    fun mapDtoToDbModel(dto: CoinInfoDto): CoinInfoDbModel = CoinInfoDbModel(
        fromSymbol = dto.fromSymbol,
        toSymbol = dto.toSymbol,
        price = dto.price,
        lastMarket = dto.lastMarket,
        lastUpdate = dto.lastUpdate,
        highDay = dto.highDay,
        lowDay = dto.lowDay,
        imageUrl = BASE_IMAGE_URL + dto.imageUrl
    )

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto
    ): List<CoinInfoDto> {

        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJsonObject = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJsonObject.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJsonObject.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map {
                it1 -> it1.coinName?.name
        }?.joinToString(",") ?: "BTC"
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel): CoinInfo = CoinInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        lastMarket = dbModel.lastMarket,
        lastUpdate = convertTimestampToTime(dbModel.lastUpdate),
        highDay = dbModel.highDay,
        lowDay = dbModel.lowDay,
        imageUrl =  dbModel.imageUrl
    )

    private fun convertTimestampToTime (timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object{
       private const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }
}
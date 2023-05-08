package com.gmail.pashkovich.al.cryptoapp.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.gmail.pashkovich.al.cryptoapp.data.database.AppDatabase
import com.gmail.pashkovich.al.cryptoapp.data.mapper.CoinMapper
import com.gmail.pashkovich.al.cryptoapp.data.network.ApiFactory
import com.gmail.pashkovich.al.cryptoapp.domain.CoinInfo
import com.gmail.pashkovich.al.cryptoapp.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    private val application: Application
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()

    private val mapper = CoinMapper()

    private val apiService = ApiFactory.apiService

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return coinInfoDao.getPriceList().map {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 20)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoListDto = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoListDto.map {
                    mapper.mapDtoToDbModel(it)
                }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }

    }
}
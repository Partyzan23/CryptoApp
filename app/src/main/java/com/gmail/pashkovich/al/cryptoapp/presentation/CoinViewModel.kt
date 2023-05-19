package com.gmail.pashkovich.al.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gmail.pashkovich.al.cryptoapp.data.repository.CoinRepositoryImpl
import com.gmail.pashkovich.al.cryptoapp.domain.GetCoinInfoListUseCase
import com.gmail.pashkovich.al.cryptoapp.domain.GetCoinInfoUseCase
import com.gmail.pashkovich.al.cryptoapp.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val loadDataUseCase = LoadDataUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}
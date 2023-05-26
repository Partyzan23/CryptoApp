package com.gmail.pashkovich.al.cryptoapp.presentation

import androidx.lifecycle.ViewModel
import com.gmail.pashkovich.al.cryptoapp.domain.GetCoinInfoListUseCase
import com.gmail.pashkovich.al.cryptoapp.domain.GetCoinInfoUseCase
import com.gmail.pashkovich.al.cryptoapp.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}
package com.gmail.pashkovich.al.cryptoapp.domain

class GetCoinInfoListUseCase(
    private val coinRepository: CoinRepository
) {

    operator fun invoke() = coinRepository.getCoinInfoList()
}
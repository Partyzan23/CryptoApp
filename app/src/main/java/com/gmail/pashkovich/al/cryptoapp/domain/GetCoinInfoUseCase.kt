package com.gmail.pashkovich.al.cryptoapp.domain

class GetCoinInfoUseCase(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(fromSymbol: String) = coinRepository.getCoinInfo(fromSymbol)
}
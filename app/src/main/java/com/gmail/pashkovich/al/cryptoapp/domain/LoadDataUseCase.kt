package com.gmail.pashkovich.al.cryptoapp.domain

class LoadDataUseCase(
    private val coinRepository: CoinRepository
) {
   operator fun invoke() = coinRepository.loadData()
}
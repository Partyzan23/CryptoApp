package com.gmail.pashkovich.al.cryptoapp.domain

class LoadDataUseCase(
    private val coinRepository: CoinRepository
) {
   suspend operator fun invoke() = coinRepository.loadData()
}
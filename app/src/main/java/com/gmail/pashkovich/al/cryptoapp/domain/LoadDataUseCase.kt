package com.gmail.pashkovich.al.cryptoapp.domain

import javax.inject.Inject

class LoadDataUseCase @Inject constructor (
    private val coinRepository: CoinRepository
) {
   operator fun invoke() = coinRepository.loadData()
}
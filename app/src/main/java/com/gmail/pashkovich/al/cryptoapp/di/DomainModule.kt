package com.gmail.pashkovich.al.cryptoapp.di

import com.gmail.pashkovich.al.cryptoapp.data.repository.CoinRepositoryImpl
import com.gmail.pashkovich.al.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindRepository(impl: CoinRepositoryImpl): CoinRepository
}
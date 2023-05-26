package com.gmail.pashkovich.al.cryptoapp.di

import androidx.lifecycle.ViewModel
import com.gmail.pashkovich.al.cryptoapp.presentation.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    @Binds
    fun bindCoinVewModel(impl: CoinViewModel): ViewModel
}
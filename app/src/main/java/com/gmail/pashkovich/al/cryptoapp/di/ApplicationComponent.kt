package com.gmail.pashkovich.al.cryptoapp.di

import com.gmail.pashkovich.al.cryptoapp.presentation.CoinPriceListActivity
import dagger.Component

@Component(modules = [DomainModule::class])
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)
}
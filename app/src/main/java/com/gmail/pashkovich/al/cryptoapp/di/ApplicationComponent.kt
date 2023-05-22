package com.gmail.pashkovich.al.cryptoapp.di

import android.app.Application
import com.gmail.pashkovich.al.cryptoapp.presentation.CoinDetailFragment
import com.gmail.pashkovich.al.cryptoapp.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    DataModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
package com.gmail.pashkovich.al.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.gmail.pashkovich.al.cryptoapp.data.workers.CoinWorkerFactory
import com.gmail.pashkovich.al.cryptoapp.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CoinWorkerFactory

    val component by lazy {

        DaggerApplicationComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}
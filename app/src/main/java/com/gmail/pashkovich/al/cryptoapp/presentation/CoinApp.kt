package com.gmail.pashkovich.al.cryptoapp.presentation

import android.app.Application
import com.gmail.pashkovich.al.cryptoapp.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {

        DaggerApplicationComponent.factory()
            .create(this)
    }
}
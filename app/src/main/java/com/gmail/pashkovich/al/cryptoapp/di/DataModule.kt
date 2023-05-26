package com.gmail.pashkovich.al.cryptoapp.di

import android.app.Application
import com.gmail.pashkovich.al.cryptoapp.data.database.AppDatabase
import com.gmail.pashkovich.al.cryptoapp.data.database.CoinInfoDao
import com.gmail.pashkovich.al.cryptoapp.data.network.ApiFactory
import com.gmail.pashkovich.al.cryptoapp.data.network.ApiService
import com.gmail.pashkovich.al.cryptoapp.data.repository.CoinRepositoryImpl
import com.gmail.pashkovich.al.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object{

        @ApplicationScope
        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService{
            return ApiFactory.apiService
        }
    }
}
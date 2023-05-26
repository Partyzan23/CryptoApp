package com.gmail.pashkovich.al.cryptoapp.di

import androidx.work.ListenableWorker
import com.gmail.pashkovich.al.cryptoapp.data.workers.ChildWorkerFactory
import com.gmail.pashkovich.al.cryptoapp.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    @Binds
    fun bindRefreshDataWorkerFactory(impl: RefreshDataWorker.Factory): ChildWorkerFactory
}
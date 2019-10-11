package com.siddhi.lowesgig.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.siddhi.lowesgig.LowesGigApp
import com.siddhi.lowesgig.data.local.db.DatabaseService
import com.siddhi.lowesgig.data.repository.HeadlinesRepository
import com.siddhi.lowesgig.di.ApplicationContext
import com.siddhi.lowesgig.di.module.ApplicationModule
import com.siddhi.lowesgig.network.NetworkService
import com.siddhi.lowesgig.utils.network.NetworkHelper
import com.siddhi.lowesgig.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: LowesGigApp)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getSharedPreferences(): SharedPreferences

    fun getNetworkHelper(): NetworkHelper

    fun getHeadlinesRepository(): HeadlinesRepository

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable
}
package com.siddhi.lowesgig.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.siddhi.lowesgig.BuildConfig
import com.siddhi.lowesgig.LowesGigApp
import com.siddhi.lowesgig.data.local.db.DatabaseService
import com.siddhi.lowesgig.di.ApplicationContext
import com.siddhi.lowesgig.network.NetworkService
import com.siddhi.lowesgig.network.Networking
import com.siddhi.lowesgig.utils.network.NetworkHelper
import com.siddhi.lowesgig.utils.rx.RxSchedulerProvider
import com.siddhi.lowesgig.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: LowesGigApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    /**
     * Since this function does not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences("lowes-project-prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "lowes-project-db"
        ).build()

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.API_KEY,
            BuildConfig.BASE_URL
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}
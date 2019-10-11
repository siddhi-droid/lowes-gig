package com.siddhi.lowesgig.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.siddhi.lowesgig.data.repository.HeadlinesRepository
import com.siddhi.lowesgig.ui.base.BaseActivity
import com.siddhi.lowesgig.ui.headlines.HeadlinesAdapter
import com.siddhi.lowesgig.ui.headlines.HeadlinesViewModel
import com.siddhi.lowesgig.utils.ViewModelProviderFactory
import com.siddhi.lowesgig.utils.network.NetworkHelper
import com.siddhi.lowesgig.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideHeadlinesViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        headlinesRepository: HeadlinesRepository
    ): HeadlinesViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(HeadlinesViewModel::class) {
            HeadlinesViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                headlinesRepository,
                ArrayList()
            )
        }).get(HeadlinesViewModel::class.java)

    @Provides
    fun providePostsAdapter() = HeadlinesAdapter(activity.lifecycle, ArrayList())
}
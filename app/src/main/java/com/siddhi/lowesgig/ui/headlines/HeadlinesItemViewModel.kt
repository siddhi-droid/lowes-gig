package com.siddhi.lowesgig.ui.headlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.siddhi.lowesgig.data.model.ArticlesItem
import com.siddhi.lowesgig.ui.base.BaseItemViewModel
import com.siddhi.lowesgig.utils.common.Resource
import com.siddhi.lowesgig.utils.logger.Logger
import com.siddhi.lowesgig.utils.network.NetworkHelper
import com.siddhi.lowesgig.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HeadlinesItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseItemViewModel<ArticlesItem>(schedulerProvider, compositeDisposable, networkHelper) {

    companion object {
        const val TAG = "HeadlinesItemViewModel"
    }

    val urlToImage: LiveData<String> = Transformations.map(data) { it.urlToImage }
    val title: LiveData<String> = Transformations.map(data) { it.title }
    val newsPublishedAt: LiveData<String?> = Transformations.map(data) { it.publishedAt }
    val newsSource: LiveData<String?> = Transformations.map(data) { it.source?.name }
    val desc: LiveData<String?> = Transformations.map(data) { it.description }

    fun onItemClick(position: Int) {
        messageString.postValue(Resource.success("onItemClick at $position of ${data.value?.author}"))
        Logger.d(TAG, "onItemClick at $position")
    }

    override fun onCreate() {
        Logger.d(TAG, "onCreate called")
    }
}

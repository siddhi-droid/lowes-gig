package com.siddhi.lowesgig.ui.headlines

import androidx.lifecycle.MutableLiveData
import com.siddhi.lowesgig.data.model.ArticlesItem
import com.siddhi.lowesgig.data.repository.HeadlinesRepository
import com.siddhi.lowesgig.ui.base.BaseViewModel
import com.siddhi.lowesgig.utils.common.Resource
import com.siddhi.lowesgig.utils.network.NetworkHelper
import com.siddhi.lowesgig.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class HeadlinesViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val headlinesRepository: HeadlinesRepository,
    private val allArticles: ArrayList<ArticlesItem>
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val articles: MutableLiveData<Resource<List<ArticlesItem>>> = MutableLiveData()
    val offlineMode: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreate() {
    }

    fun fetchHeadlines(country: String) {
        if (checkInternetConnectionWithMessage()) {
            loading.postValue(true)
            compositeDisposable.addAll(
                headlinesRepository.getHeadlines(country)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe({
                        if (it.status == "ok" && it.totalResults != 0) {
                            allArticles.addAll(it.articles)
                            headlinesRepository.saveArticles(it.articles)
                            loading.postValue(false)
                            articles.postValue(Resource.success(it.articles))
                        }
                    },
                        {
                            loading.postValue(false)
                            handleNetworkError(it)
                        })
            )
        } else {
            getLocally()
        }
    }

    private fun getLocally() {
        compositeDisposable.addAll(
            headlinesRepository.getHeadlinesFromRoomDb()
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    { it ->
                        it.forEach {
                            val articlesItem = ArticlesItem(
                                it.publishedAt,
                                "",
                                it.urlToImage,
                                it.description,
                                null,
                                it.title,
                                "",
                                ""
                            )
                            allArticles.add(articlesItem)
                            articles.postValue(Resource.success(allArticles))
                            offlineMode.postValue(true)
                        }
                    },
                    {
                    }
                )
        )
    }
}

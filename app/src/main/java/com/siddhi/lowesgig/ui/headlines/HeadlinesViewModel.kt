package com.siddhi.lowesgig.ui.headlines

import androidx.lifecycle.MutableLiveData
import com.siddhi.lowesgig.data.local.entity.NewsEntity
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

    var country: MutableLiveData<String> = MutableLiveData()
    private lateinit var localRoomList: List<NewsEntity?>
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val articles: MutableLiveData<Resource<List<ArticlesItem>>> = MutableLiveData()

    override fun onCreate() {
    }

    fun fetchHeadlines(country: String) {
        //loading.postValue(true)
        if (checkInternetConnectionWithMessage()) {
            compositeDisposable.addAll(
                headlinesRepository.getHeadlines(country)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe({
                        //if (it.status.equals("ok")) {
                        /*try {
                            for (articleItems in it.articles!!.indices) {
                                val articleObject = NewsEntity(
                                    it.articles[articleItems]?.publishedAt!!,
                                    it.articles[articleItems]?.urlToImage!!,
                                    it.articles[articleItems]?.description!!,
                                    it.articles[articleItems]?.source?.name!!,
                                    it.articles[articleItems]?.title!!
                                )
                                articleRoomList.add(articleObject)
                            }
                            headlinesRepository.saveArticles(articleRoomList)
                            loading.postValue(false)
                            headlinesEvent.postValue(Resource.success(it))
                        } catch (e: Exception) {
                        }*/
                        allArticles.addAll(it)
                        //loading.postValue(false)
                        articles.postValue(Resource.success(it))
                        //}
                    },
                        {
                            handleNetworkError(it)
                            //loggingIn.postValue(false)
                        })
            )
        } else {
            localRoomList = headlinesRepository.getHeadlinesFromRoomDb()
        }
    }
}

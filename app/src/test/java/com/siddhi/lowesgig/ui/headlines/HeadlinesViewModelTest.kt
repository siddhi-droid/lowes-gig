package com.siddhi.lowesgig.ui.headlines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.siddhi.lowesgig.R
import com.siddhi.lowesgig.data.model.ArticlesItem
import com.siddhi.lowesgig.data.remote.HeadlinesResponse
import com.siddhi.lowesgig.data.repository.HeadlinesRepository
import com.siddhi.lowesgig.utils.common.Resource
import com.siddhi.lowesgig.utils.network.NetworkHelper
import com.siddhi.lowesgig.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HeadlinesViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var headlinesRepository: HeadlinesRepository

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Mock
    private lateinit var allArticles: ArrayList<ArticlesItem>

    @Mock
    private lateinit var articlesObserver: Observer<Resource<List<ArticlesItem>>>

    @Mock
    private lateinit var offlineObserver: Observer<Boolean>

    @Mock
    private lateinit var messsageStringIdObserver: Observer<Resource<Int>>

    private lateinit var testScheduler: TestScheduler

    private lateinit var headlinesViewModel: HeadlinesViewModel

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        headlinesViewModel = HeadlinesViewModel(
            testSchedulerProvider,
            compositeDisposable,
            networkHelper,
            headlinesRepository,
            allArticles
        )
        headlinesViewModel.loading.observeForever(loadingObserver)
        headlinesViewModel.articles.observeForever(articlesObserver)
        headlinesViewModel.messageStringId.observeForever(messsageStringIdObserver)
        headlinesViewModel.offlineMode.observeForever(offlineObserver)
    }

    @Test
    fun givenStatusOK_whenFetchingHeadlines_shouldShowHeadlines() {
        val country = "in"
        val headlinesResponse = HeadlinesResponse(3, allArticles, "ok")
        doReturn(true)
            .`when`(networkHelper)
            .isNetworkConnected()
        doReturn(Single.just(headlinesResponse))
            .`when`(headlinesRepository)
            .getHeadlines(country)
        /*doReturn(Single.just(headlinesResponse))
            .`when`(headlinesRepository)
            .saveArticles(headlinesResponse.articles)*/
        headlinesViewModel.fetchHeadlines(country)
        testScheduler.triggerActions()
        verify(allArticles).addAll(headlinesResponse.articles)
        verify(headlinesRepository).saveArticles(headlinesResponse.articles)
        assert(headlinesViewModel.loading.value == false)
        //assert(headlinesViewModel.articles.value == headlinesResponse.articles)
        verify(loadingObserver).onChanged(true)
        verify(loadingObserver).onChanged(false)
        //verify(articlesObserver).onChanged(Resource.success(listOf()))
    }

    @Test
    fun givenNoConnectivity_whenFetchingHeadlines_shouldShowNetworkError() {
        val country = "in"
        doReturn(false)
            .`when`(networkHelper)
            .isNetworkConnected()
        headlinesViewModel.fetchHeadlines(country)
        assert(headlinesViewModel.messageStringId.value == Resource.error(R.string.network_connection_error))
        verify(messsageStringIdObserver).onChanged(Resource.error(R.string.network_connection_error))
    }

    @After
    fun tearDown() {
        headlinesViewModel.loading.removeObserver(loadingObserver)
        headlinesViewModel.articles.removeObserver(articlesObserver)
        headlinesViewModel.offlineMode.removeObserver(offlineObserver)
    }
}
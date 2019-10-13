package com.siddhi.lowesgig.ui.headlines

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.siddhi.lowesgig.R
import com.siddhi.lowesgig.di.component.ActivityComponent
import com.siddhi.lowesgig.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HeadlinesActivity : BaseActivity<HeadlinesViewModel>() {

    companion object {
        const val TAG = "HeadlinesActivity"
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var headlinesAdapter: HeadlinesAdapter

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewModel.fetchHeadlines("in")
        rv_news.apply {
            layoutManager = linearLayoutManager
            adapter = headlinesAdapter
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.loading.observe(this, Observer {
            progress_headlines.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.articles.observe(this, Observer {
            it.data?.run { headlinesAdapter.appendData(this) }
        })

        viewModel.offlineMode.observe(this, Observer {
            if (it) showMessage("OFFLINE MODE")
        })
    }
}
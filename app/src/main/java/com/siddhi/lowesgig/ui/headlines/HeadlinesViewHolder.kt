package com.siddhi.lowesgig.ui.headlines

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.siddhi.lowesgig.R
import com.siddhi.lowesgig.data.model.ArticlesItem
import com.siddhi.lowesgig.di.component.ViewHolderComponent
import com.siddhi.lowesgig.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.item_view_headlines.view.*

class HeadlinesViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<ArticlesItem, HeadlinesItemViewModel>(R.layout.item_view_headlines, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.urlToImage.observe(this, Observer {
            Glide.with(itemView.context).load(it).into(itemView.iv_news_source)
        })
        viewModel.title.observe(this, Observer {
            itemView.tv_main_headline.text = it
        })
        viewModel.newsSource.observe(this, Observer {
            itemView.tv_news_source.text = it
        })
        viewModel.newsPublishedAt.observe(this, Observer {
            itemView.tv_news_published_at.text = it
        })
    }

    override fun setupView(view: View) {
        view.setOnClickListener {
            viewModel.onItemClick(adapterPosition)
        }
    }
}

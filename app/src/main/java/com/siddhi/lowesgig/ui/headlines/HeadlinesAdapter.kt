package com.siddhi.lowesgig.ui.headlines

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.siddhi.lowesgig.data.model.ArticlesItem
import com.siddhi.lowesgig.ui.base.BaseAdapter

class HeadlinesAdapter(
    parentLifecycle: Lifecycle,
    private val articlesItem: ArrayList<ArticlesItem>
) : BaseAdapter<ArticlesItem, HeadlinesViewHolder>(parentLifecycle, articlesItem) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HeadlinesViewHolder(parent)
}
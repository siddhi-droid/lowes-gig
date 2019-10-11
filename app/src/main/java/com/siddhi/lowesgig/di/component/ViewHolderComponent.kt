package com.siddhi.lowesgig.di.component

import com.siddhi.lowesgig.di.ViewModelScope
import com.siddhi.lowesgig.di.module.ViewHolderModule
import com.siddhi.lowesgig.ui.headlines.HeadlinesViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: HeadlinesViewHolder)
}
package com.siddhi.lowesgig.di.module

import androidx.lifecycle.LifecycleRegistry
import com.siddhi.lowesgig.di.ViewModelScope
import com.siddhi.lowesgig.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}
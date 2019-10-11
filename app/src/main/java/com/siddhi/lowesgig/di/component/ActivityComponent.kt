package com.siddhi.lowesgig.di.component

import com.siddhi.lowesgig.di.ActivityScope
import com.siddhi.lowesgig.di.module.ActivityModule
import com.siddhi.lowesgig.ui.headlines.HeadlinesActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(activity: HeadlinesActivity)
}
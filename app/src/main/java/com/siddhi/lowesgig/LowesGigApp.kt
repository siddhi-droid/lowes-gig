package com.siddhi.lowesgig

import android.app.Application
import com.siddhi.lowesgig.di.component.ApplicationComponent
import com.siddhi.lowesgig.di.component.DaggerApplicationComponent
import com.siddhi.lowesgig.di.module.ApplicationModule

class LowesGigApp : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}
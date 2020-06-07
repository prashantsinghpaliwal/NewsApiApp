package com.bigsteptech.wynknewsapp

import android.app.Application
import com.bigsteptech.wynknewsapp.di.module.ApplicationModule
import com.bigsteptech.wynknewsapp.di.component.ApplicationComponent
import com.bigsteptech.wynknewsapp.di.component.DaggerApplicationComponent

class MyApplication : Application() {

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
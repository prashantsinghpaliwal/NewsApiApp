package com.bigsteptech.wynknewsapp.di.component

import com.bigsteptech.wynknewsapp.di.ActivityScope
import com.bigsteptech.wynknewsapp.ui.main.MainActivity
import com.bigsteptech.wynknewsapp.di.module.ActivityModule
import com.bigsteptech.wynknewsapp.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {


    fun inject(activity: MainActivity)

    fun inject(activity: SplashActivity)
}
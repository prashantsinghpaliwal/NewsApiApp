package com.bigsteptech.wynknewsapp.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigsteptech.wynknewsapp.ui.base.BaseActivity
import com.bigsteptech.wynknewsapp.ui.main.MainViewModel
import com.bigsteptech.wynknewsapp.ui.splash.SplashViewModel
import com.bigsteptech.wynknewsapp.utils.ViewModelProviderFactory
import com.bigsteptech.wynknewsapp.utils.network.NetworkHelper

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)


    @Provides
    fun provideMainViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(
                compositeDisposable,
                networkHelper
            )
        }).get(MainViewModel::class.java)

    @Provides
    fun provideSplashViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): SplashViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(
                compositeDisposable,
                networkHelper
            )
        }).get(SplashViewModel::class.java)

}
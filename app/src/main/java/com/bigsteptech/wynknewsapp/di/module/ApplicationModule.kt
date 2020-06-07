package com.bigsteptech.wynknewsapp.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.bigsteptech.wynknewsapp.BuildConfig
import com.bigsteptech.wynknewsapp.di.ApplicationContext

import com.bigsteptech.wynknewsapp.MyApplication
import com.bigsteptech.wynknewsapp.data.remote.NetworkService
import com.bigsteptech.wynknewsapp.data.remote.Networking
import com.bigsteptech.wynknewsapp.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()


    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences("airtel-wynk-project-prefs", Context.MODE_PRIVATE)
//BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.API_KEY,
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}
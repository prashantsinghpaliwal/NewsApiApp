package com.bigsteptech.wynknewsapp.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.bigsteptech.wynknewsapp.di.ApplicationContext
import com.bigsteptech.wynknewsapp.MyApplication
import com.bigsteptech.wynknewsapp.data.remote.NetworkService
import com.bigsteptech.wynknewsapp.di.module.ApplicationModule
import com.bigsteptech.wynknewsapp.utils.network.NetworkHelper
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: MyApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getSharedPreferences(): SharedPreferences

    fun getCompositeDisposable(): CompositeDisposable


    fun getNetworkHelper(): NetworkHelper


}
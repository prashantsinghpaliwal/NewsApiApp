package com.bigsteptech.wynknewsapp.ui.main

import com.bigsteptech.wynknewsapp.ui.base.BaseViewModel
import com.bigsteptech.wynknewsapp.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(compositeDisposable, networkHelper) {


    override fun onCreate() {

    }
}
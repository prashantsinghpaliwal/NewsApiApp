package com.bigsteptech.wynknewsapp.ui.splash


import androidx.lifecycle.MutableLiveData
import com.bigsteptech.wynknewsapp.ui.base.BaseViewModel
import com.bigsteptech.wynknewsapp.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import kotlin.concurrent.schedule


class SplashViewModel(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(compositeDisposable, networkHelper) {

    val launchMain: MutableLiveData<Map<String, String>> = MutableLiveData()
    val launchLogin: MutableLiveData<Map<String, String>> = MutableLiveData()

    override fun onCreate() {

        Timer().schedule(3000) {
            launchMain.postValue(emptyMap())
        }

    }
}




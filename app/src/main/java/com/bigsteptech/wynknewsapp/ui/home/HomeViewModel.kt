package com.bigsteptech.wynknewsapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bigsteptech.wynknewsapp.data.remote.response.Articles
import com.bigsteptech.wynknewsapp.data.repository.NewsRepository
import com.bigsteptech.wynknewsapp.ui.base.BaseViewModel
import com.bigsteptech.wynknewsapp.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val newsRepository: NewsRepository
) : BaseViewModel(compositeDisposable, networkHelper) {

    val shimmerLiveData = MutableLiveData<Boolean>()
    val errorScreenLiveData = MutableLiveData<String>()

    val topNewsListLiveData = MutableLiveData<List<Articles>>()
    val otherNewsListLiveData = MutableLiveData<List<Articles>>()

    override fun onCreate() {


    }

    fun fetchTopHeadLines(pageNumber: String, pageSize: String) {
        compositeDisposable.add(
            newsRepository.fetchNewsApiResponse("in", pageSize, pageNumber,true)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.v("HomeViewModel", "Top Headlines $it")
                    topNewsListLiveData.postValue(it.articles)
                    shimmerLiveData.postValue(true)

                }, {

                    Log.v("HomeViewModel", "error $it")
                    shimmerLiveData.postValue(true)
                    errorScreenLiveData.postValue(it.toString())
                })
        )
    }

    fun fetchOtherNews(pageNumber: String, pageSize: String) {
        compositeDisposable.add(
            newsRepository.fetchNewsApiResponse("in", pageSize, pageNumber,false)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.v("HomeViewModel", "OtherNews $it")
                    otherNewsListLiveData.postValue(it.articles)

                }, {
                    Log.v("HomeViewModel", "error $it")
                    shimmerLiveData.postValue(true)
                    errorScreenLiveData.postValue(it.toString())
                })
        )
    }
}
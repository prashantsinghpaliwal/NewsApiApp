package com.bigsteptech.wynknewsapp.data.repository

import com.bigsteptech.wynknewsapp.data.remote.NetworkService
import com.bigsteptech.wynknewsapp.data.remote.response.NewsApiResponse
import io.reactivex.Single
import javax.inject.Inject

class NewsRepository @Inject constructor(private val networkService: NetworkService) {

    fun fetchNewsApiResponse(
        country: String,
        pageSize:String,
        pageNumber:String,
        isTopNewsRequest:Boolean
    ): Single<NewsApiResponse> {
        if (isTopNewsRequest)
            return networkService.doTopHeadLineApiCall(
                country,
                pageSize,
                pageNumber
            )
        else
            return networkService.doOtherNewsApiCall(
                pageSize,
                pageNumber,"entertainment"
            )
    }
}
package com.bigsteptech.wynknewsapp.data.remote

import com.bigsteptech.wynknewsapp.data.remote.response.NewsApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(Endpoints.TOP_HEADLINES)
    fun doTopHeadLineApiCall(
        @Query("country") country: String?,
        @Query("pageSize") pageSize: String?,
        @Query("page") page: String?,
        @Query(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<NewsApiResponse>


    @GET(Endpoints.OTHER_NEWS)
    fun doOtherNewsApiCall(
        @Query("pageSize") pageSize: String?,
        @Query("page") page: String?,
        @Query("q") query: String?,
        @Query(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<NewsApiResponse>


}
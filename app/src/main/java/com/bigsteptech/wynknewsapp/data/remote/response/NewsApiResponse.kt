package com.bigsteptech.wynknewsapp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONArray


data class NewsApiResponse(

    @Expose
    @SerializedName("articles")
    val articles: List<Articles>,

    @Expose
    @SerializedName("totalResults")
    val totalResults: Int
)
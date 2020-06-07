package com.bigsteptech.wynknewsapp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Articles (

    @Expose
    @SerializedName("source")
    val source: Source,


    @Expose
    @SerializedName("author")
    val author: String,

    @Expose
    @SerializedName("content")
    val content: String,


    @Expose
    @SerializedName("description")
    val description: String,


    @Expose
    @SerializedName("publishedAt")
    val publishedAt: String,


    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("url")
    val url: String,

    @Expose
    @SerializedName("urlToImage")
    val urlToImage: String
)
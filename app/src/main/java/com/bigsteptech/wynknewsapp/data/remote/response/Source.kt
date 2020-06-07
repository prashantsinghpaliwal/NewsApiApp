package com.bigsteptech.wynknewsapp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Source (

    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("name")
    val name: String
)
package com.bigsteptech.wynknewsapp.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import java.io.IOException
import java.net.ConnectException
import javax.inject.Singleton

@Singleton
class NetworkHelper constructor(private val context: Context) {

    companion object {
        private const val TAG = "NetworkHelper"
    }

    fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }
}
package com.ahmedobied.ricknmorty.data.network.common

import android.content.Context
import android.net.ConnectivityManager
import com.ahmedobied.ricknmorty.data.network.common.ConnectivityInterceptor
import com.ahmedobied.ricknmorty.internal.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(
    context: Context
) : ConnectivityInterceptor {
    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected())
            throw NoNetworkException()

        return  chain.proceed(chain.request())
    }

    private fun isConnected(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return  connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }
}
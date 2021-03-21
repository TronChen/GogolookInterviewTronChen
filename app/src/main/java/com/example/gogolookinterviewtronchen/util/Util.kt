package com.example.gogolookinterviewtronchen.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.gogolookinterviewtronchen.GogolookApplication

object Util {

    fun isInternetConnected(): Boolean {
        val cm = GogolookApplication.INSTANCE
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun getString(resourceId: Int): String {
        return GogolookApplication.INSTANCE.getString(resourceId)
    }
}
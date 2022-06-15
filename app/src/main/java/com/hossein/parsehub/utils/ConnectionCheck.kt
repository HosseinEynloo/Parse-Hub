package com.hossein.parsehub.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

class ConnectionCheck(context: Context?) {
    var c = context;
    lateinit var connectivity: ConnectivityManager
     fun isConnected(): Boolean {
        connectivity = c?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
        if (Build.VERSION_CODES.Q <= Build.VERSION.SDK_INT) {
            val capabilites = connectivity.getNetworkCapabilities(connectivity.activeNetwork)
            if (capabilites != null) {
                when {
                    capabilites.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilites.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilites.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val info: NetworkInfo? = connectivity.activeNetworkInfo
            if (info != null && info.isConnected) {
                return true
            }
        }
        return false
    }
}

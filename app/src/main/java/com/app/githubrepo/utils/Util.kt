package com.app.githubrepo.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.app.githubrepo.R

object Util {
    fun openUrlToWebView(htmlUrl: String?, context: Context) {
        try {
            var url = htmlUrl
            if (!url!!.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://$url"
            }
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isInternetConnection(context: Context): Boolean {
        var isAvailable = true
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder = NetworkRequest.Builder()
        cm.registerNetworkCallback(builder.build(), @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isAvailable = true
            }

            override fun onLost(network: Network) {
                isAvailable = false
            }
        })
        return isAvailable
    }

    fun showToast(context: Context, message: String?) {
        if (message?.isNotEmpty()!!) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(
                context,
                context.getString(R.string.error_something_went_wrong),
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}
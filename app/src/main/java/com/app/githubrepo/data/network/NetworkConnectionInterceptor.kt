package com.app.githubrepo.data.network

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.app.githubrepo.R
import com.app.githubrepo.utils.Util
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun intercept(chain: Interceptor.Chain): Response? {
        if (!Util.isInternetConnection(context)) {
            throw IOException(context.getString(R.string.error_no_internet))
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

}
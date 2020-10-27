package com.app.githubrepo.data.network

import com.app.githubrepo.MyApplication
import com.app.githubrepo.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private val okHttpClient: OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(
            NetworkConnectionInterceptor(MyApplication.getInstance()!!)
        ).connectTimeout(
            30,
            TimeUnit.SECONDS
        )
            .readTimeout(30, TimeUnit.SECONDS).build()

    private fun builder(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    val apiInterface: ApiInterface = builder().create(ApiInterface::class.java)

}
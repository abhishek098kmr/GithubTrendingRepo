package com.app.githubrepo.data.network

import com.app.githubrepo.data.model.ResponseStatus
import com.app.githubrepo.data.model.TrendingRepoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/repositories")
    suspend fun getTrendingRepo(@Query("q") q: String, @Query("page") page: Int):TrendingRepoResponse
}
package com.app.githubrepo.data.network

import com.app.githubrepo.data.model.TrendingRepoResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET
   suspend fun getTrendingRepo():Response<List<TrendingRepoResponse>>
}
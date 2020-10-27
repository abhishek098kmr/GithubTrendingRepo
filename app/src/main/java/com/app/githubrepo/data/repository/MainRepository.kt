package com.app.githubrepo.data.repository

import com.app.githubrepo.data.network.RetrofitBuilder

class MainRepository {
    suspend fun getTrendingRepo(q:String,page:Int) = RetrofitBuilder.apiInterface.getTrendingRepo(q,page)

}
package com.app.githubrepo.data.model

sealed class ResponseStatus<TrendingRepoResponse> {
    data class Success<TrendingRepoResponse>(val value: TrendingRepoResponse) :
        ResponseStatus<TrendingRepoResponse>()

    data class Error<TrendingRepoResponse>(val value: TrendingRepoResponse) :
        ResponseStatus<TrendingRepoResponse>()


}
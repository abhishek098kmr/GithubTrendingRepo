package com.app.githubrepo.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class TrendingRepoResponse(
    val isLoading: Boolean=false,
    val items: @RawValue
    List<Items>, val errorResponse: ErrorResponse?
) {

    @Parcelize
    data class Items(
        val id: String,
        val name: String,
        val html_url: String,
        val description: String,
        val language: String,
        val score: String,
        val forks: String,
        val owner: @RawValue Owner
    ) : Parcelable


    @Parcelize
    data class Owner(
        val html_url: String,
        val avatar_url: String,
        val login: String
    ) : Parcelable
}
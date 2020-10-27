package com.app.githubrepo.ui.interfaces

import com.app.githubrepo.data.model.TrendingRepoResponse

interface FragmentCallbackListener {
    fun openRepoDetailFragment(items: TrendingRepoResponse.Items)
    fun addTitleToToolbar(title: String, isHomeButtonEnabled: Boolean)
}
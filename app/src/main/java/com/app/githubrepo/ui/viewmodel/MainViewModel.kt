package com.app.githubrepo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.githubrepo.MyApplication
import com.app.githubrepo.R
import com.app.githubrepo.data.model.ErrorResponse
import com.app.githubrepo.data.model.TrendingRepoResponse
import com.app.githubrepo.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import java.io.IOException

class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    fun getTrendingRepo(q: String, page: Int) = liveData(Dispatchers.IO) {
        emit(TrendingRepoResponse(true, emptyList(), null))
        try {
            emit(repository.getTrendingRepo(q, page))
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    val errorResponse = ErrorResponse(
                        MyApplication.getInstance()!!.getString(R.string.error_no_internet)
                    )
                    emit(TrendingRepoResponse(false, emptyList(), errorResponse))
                }
                else -> {
                    val errorResponse = ErrorResponse(
                        MyApplication.getInstance()!!.getString(R.string.error_something_went_wrong)
                    )
                    emit(TrendingRepoResponse(false, emptyList(), errorResponse))
                }
            }
        }
    }

}
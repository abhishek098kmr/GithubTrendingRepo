package com.app.githubrepo.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.githubrepo.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    fun getTrendingRepo(q:String,page:Int) = liveData(Dispatchers.IO) {
        try {
            emit(repository.getTrendingRepo(q, page))
        }catch (t:Throwable){
            Log.e("error",t.toString())
        }
    }

}
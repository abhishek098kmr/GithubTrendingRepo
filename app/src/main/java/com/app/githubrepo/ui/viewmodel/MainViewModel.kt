package com.app.githubrepo.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.app.githubrepo.data.repository.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository=MainRepository(application)
}
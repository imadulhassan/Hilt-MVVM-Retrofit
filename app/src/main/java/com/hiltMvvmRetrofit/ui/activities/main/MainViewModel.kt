package com.hiltMvvmRetrofit.ui.activities.main

import android.util.Log
import com.hiltMvvmRetrofit.base.BaseViewModel
import com.hiltMvvmRetrofit.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(mainRepository: MainRepository): BaseViewModel(mainRepository){

    init {
        Log.d("TAG", "MainViewModel: ")
    }

}
package com.hiltMvvmRetrofit.base

import com.hiltMvvmRetrofit.repo.MainRepository


abstract class BaseViewModel( mainRepository: MainRepository): CommonViewModel(mainRepository) {



}
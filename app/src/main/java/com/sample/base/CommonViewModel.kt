package com.sample.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.repo.Resource
import com.sample.repo.Status
import javax.inject.Inject


open class CommonViewModel @Inject constructor() : ViewModel() {


    private var result: MutableLiveData<Resource<BaseResponse>>? =
        MutableLiveData<Resource<BaseResponse>>()
    private var message: MutableLiveData<String>? = null

    fun getMessage(): MutableLiveData<String> {
        return message ?: MutableLiveData<String>()
    }

    fun <T> getResult(): MutableLiveData<Resource<BaseResponse>>? {
        return result
    }

    fun <T : BaseResponse> processResponse(response: Resource<T>) {
        when (response.status) {
            Status.SUCCESS -> getResult<BaseResponse>()?.postValue(response)
            Status.ERROR -> getMessage().postValue(response.message)
            else -> {}
        }
    }


}
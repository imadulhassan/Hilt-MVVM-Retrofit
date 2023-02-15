package com.hiltMvvmRetrofit.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hiltMvvmRetrofit.models.NetworkErrorMessage
import com.hiltMvvmRetrofit.repo.MainRepository
import com.hiltMvvmRetrofit.repo.Resource
import com.hiltMvvmRetrofit.repo.Status
import com.hiltMvvmRetrofit.utils.PrefManager


open class CommonViewModel(
    private val
    mainRepository: MainRepository
) : ViewModel() {


    init {
        getLoading().postValue(false)
    }

    private var loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private var result: MutableLiveData<Resource<out BaseResponse>>? = null
    private var message: MutableLiveData<String>? = null
    private var getRetrofitErrorDataMessage: MutableLiveData<NetworkErrorMessage>? = null
    protected val retrofitErrorMessage = MutableLiveData<NetworkErrorMessage>()


    fun getLoading(): MutableLiveData<Boolean> {
        if (loading == null) loading = MutableLiveData()
        return loading
    }

    fun getMessage(): MutableLiveData<String> {
        if (message == null) message = MutableLiveData()
        return message!!
    }

    fun  getPref():PrefManager{
        return mainRepository.getPrefrance()
    }

    fun getRetrofitErrorDataMessage(): MutableLiveData<NetworkErrorMessage> {
        if (getRetrofitErrorDataMessage == null) getRetrofitErrorDataMessage = MutableLiveData()
        return getRetrofitErrorDataMessage!!
    }

    fun retrofitErrorMessage(): MutableLiveData<NetworkErrorMessage> {
        if (getRetrofitErrorDataMessage == null) getRetrofitErrorDataMessage = MutableLiveData()
        return getRetrofitErrorDataMessage!!
    }

    fun <T> getResult(): MutableLiveData<Resource<BaseResponse>> {
        if (result == null) result = MutableLiveData()
        return result!!
    }

    fun <T : BaseResponse> processResponse(response: Resource<T>) {
        when (response.status) {
            Status.SUCCESS -> getResult<BaseResponse>().postValue(response)
            Status.ERROR -> getMessage().postValue(response.message)
            else -> {}
        }
    }


    fun getRepository(): MainRepository {
        return mainRepository
    }

}
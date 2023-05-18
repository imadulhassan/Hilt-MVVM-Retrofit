package com.sample.ui.home.fragments.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.base.CommonViewModel
import com.sample.models.ApisResponse
import com.sample.models.RelatedTopic
import com.sample.repo.MainRepository
import com.sample.ui.home.DetailScreenState
import com.sample.ui.home.WorkingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(private var mainRepository: MainRepository) : CommonViewModel() {


    val homeScreenState = MutableLiveData<DetailScreenState>()
    val items = MutableLiveData<ArrayList<RelatedTopic>>()

    init {
        homeScreenState.postValue(DetailScreenState(WorkingStatus.Loading))
        getList()
    }

    fun addItems(response: ApisResponse) {
        response.let {
            response.relatedTopics.let {
                items.postValue(it as ArrayList<RelatedTopic>?)
            }
        }
    }

    private fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            processResponse(mainRepository.getCharacters())
            homeScreenState.postValue(DetailScreenState(WorkingStatus.Loaded))
        }
    }


    fun onClickItem(position: Int):RelatedTopic? {
       return items.value.let {
            it?.get(position)
        }
    }


}


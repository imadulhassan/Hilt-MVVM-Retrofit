package com.sample.ui.home.fragments.details

import androidx.lifecycle.MutableLiveData
import com.sample.base.CommonViewModel
import com.sample.models.RelatedTopic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : CommonViewModel() {

    val singleItem = MutableLiveData<RelatedTopic>()

    fun putValue(item: RelatedTopic) {
        singleItem.postValue(item)
    }

}



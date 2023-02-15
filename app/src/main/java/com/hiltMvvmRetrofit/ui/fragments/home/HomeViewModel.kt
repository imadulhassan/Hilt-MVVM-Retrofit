package com.hiltMvvmRetrofit.ui.fragments.home

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hiltMvvmRetrofit.base.BaseViewModel
import com.hiltMvvmRetrofit.models.ResultsItem
import com.hiltMvvmRetrofit.models.apiResponse
import com.hiltMvvmRetrofit.repo.MainRepository
import com.hiltMvvmRetrofit.repo.Resource
import com.hiltMvvmRetrofit.utils.checkFav
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(repository: MainRepository) : BaseViewModel(repository) {


    val homeScreenState = MutableLiveData<HomeScreenState>()
    private val items = MutableLiveData<ArrayList<ResultsItem>>()
    val itemList: LiveData<ArrayList<ResultsItem>> = items
    private val displayMode = MutableLiveData<DisplayModes>()
    private var list= MutableLiveData<ArrayList<ResultsItem>>()

    fun getDisplayMode(): LiveData<DisplayModes>? {
        return displayMode
    }

    fun switchDisplayMode() {
        val currentDisplayMode = displayMode.value!!
        if (currentDisplayMode == DisplayModes.LIST) {
            displayMode.setValue(DisplayModes.GRID)
        } else {
            displayMode.setValue(DisplayModes.LIST)
        }
    }

    init {
        viewModelScope.launch() {
            getLoading().postValue(true)
            displayMode.setValue(DisplayModes.LIST)
             getPref().getFavList().let {
                 if(it!=null){
                     it?.let {
                         list.postValue(it as ArrayList<ResultsItem>)
                     }
                 }else{
                     list.postValue(ArrayList<ResultsItem>())
                 }
            }
            getList()
        }
       }

    fun addItems(response: apiResponse) {
        response.let {
            response.results.let {
                items.postValue(it as ArrayList<ResultsItem>?)
                Log.d("Viewmodel", "addItems: added Items")
            }
        }
    }

    fun getList() {

        viewModelScope.launch(Dispatchers.IO) {

            getLoading().postValue(true)
            try {
                getRepository().getCharacters().let { response ->
                    if (response.isSuccessful) {
                        Log.d("ViewModel", "getList: ${response.code()}")
                        processResponse(Resource.success(response.body()))
                    } else {
                        Log.d("VoewModel", "getList: ${response.code()}")
                        processResponse(Resource.error(response.message(), null))
                    }
                    getLoading().postValue(false)
                }
            } catch (ex: Exception) {
                processResponse(Resource.error(ex.message!!, null))
            }
        }

    }


    fun onClickItem(position: Int, item: ResultsItem) {
        homeScreenState.postValue(HomeScreenState(WorkingStatus.onItemClick, item))
    }


    fun addToFav(item:ResultsItem, imageView: ImageView){
        viewModelScope.launch() {
            if(!checkIsFav(item)){
                list.value!!.add(item)
                Log.d("Added", "addToFav: ")
                launch(Dispatchers.Main) {
                    imageView.checkFav(true)
                    Log.d("TAG", "addToFav: ******")
                }
            }
            else{
                list.value!!.remove(item)
                Log.d("Added", "Removed: ")
                launch(Dispatchers.Main) {
                    imageView.checkFav(false)
                    Log.d("TAG", "addToFav: ++++++")

                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            getPref().saveFavList(list.value!!)
        }


    }

    fun checkIsFav(item:ResultsItem):Boolean{
        return list.value!!.contains(item)
    }



}


enum class DisplayModes {
    LIST, GRID
}


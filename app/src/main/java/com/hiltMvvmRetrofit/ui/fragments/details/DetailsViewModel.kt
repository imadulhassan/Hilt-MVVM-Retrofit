package com.hiltMvvmRetrofit.ui.fragments.details

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hiltMvvmRetrofit.base.BaseViewModel
import com.hiltMvvmRetrofit.models.EpisodeDetails
import com.hiltMvvmRetrofit.models.ResultsItem
import com.hiltMvvmRetrofit.repo.MainRepository
import com.hiltMvvmRetrofit.repo.Resource
import com.hiltMvvmRetrofit.utils.checkFav
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(repository: MainRepository) : BaseViewModel(repository) {



     val items = MutableLiveData<ResultsItem>()
     val episodeDetails= MutableLiveData<EpisodeDetails>()
    private var list= MutableLiveData<ArrayList<ResultsItem>>()


    init{

        viewModelScope.launch() {
            getPref().getFavList().let {
                if(it!=null){
                    it?.let {
                        list.postValue(it as ArrayList<ResultsItem>)
                    }
                }else{
                    list.postValue(ArrayList<ResultsItem>())
                }
            }
         }
    }

    fun  putValue(item:ResultsItem){
        items.postValue(item)
    }

    fun addEpisodeDetails(response: EpisodeDetails){
        response.let {
             episodeDetails.postValue(response)
        }
    }

    fun  getLastEpisodeDetails(id:Int){

           viewModelScope.launch(Dispatchers.IO) {
               getLoading().postValue(true)
               try{
               getRepository().getEpisodeDetails(id).let { response ->
                   if (response.isSuccessful) {
                       Log.d("Details", "getEpisode: ${response.code()}")
                       processResponse(Resource.success(response.body()))
                   } else {
                       Log.d("Details", "getEpisode: ${response.code()}")
                       processResponse(Resource.error(response.message(), null))
                   }
                   getLoading().postValue(false)
               }
               }catch (ex:Exception){
                   processResponse(Resource.error(ex.message!!, null))
               }
           }

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

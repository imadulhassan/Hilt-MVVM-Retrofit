package com.hiltMvvmRetrofit.repo


import com.hiltMvvmRetrofit.models.EpisodeDetails
import com.hiltMvvmRetrofit.models.apiResponse
import com.hiltMvvmRetrofit.repo.network.ApiService
import com.hiltMvvmRetrofit.utils.PrefManager
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor( private val apiService: ApiService, private  val prefManager: PrefManager) {


    suspend fun getCharacters(): Response<apiResponse> {
        return  apiService.getCharactersList()
    }

    suspend fun getEpisodeDetails(id:Int): Response<EpisodeDetails> {
        return  apiService.getEpisodeDetails(id)
    }

    fun getPrefrance():PrefManager{
        return  prefManager
    }
}
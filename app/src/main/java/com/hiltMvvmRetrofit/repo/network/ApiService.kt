package com.hiltMvvmRetrofit.repo.network

import com.hiltMvvmRetrofit.models.EpisodeDetails
import com.hiltMvvmRetrofit.models.apiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/character")
    suspend fun getCharactersList():Response<apiResponse>

    @GET("api/character/{page}")
    suspend fun getChractersWithPage(@Path("page") page:Int):Response<apiResponse>

    @GET("api/episode/{id}")
    suspend fun getEpisodeDetails(@Path("id") id:Int):Response<EpisodeDetails>

}
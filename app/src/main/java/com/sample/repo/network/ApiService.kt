package com.sample.repo.network


import com.sample.BuildConfig
import com.sample.models.ApisResponse
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("?q=${BuildConfig.QUERY}&format=json")
    suspend fun getCharactersList(): Response<ApisResponse>


}
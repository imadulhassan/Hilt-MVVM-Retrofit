package com.hiltMvvmRetrofit.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hiltMvvmRetrofit.models.ResultsItem

import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class PrefManager @Inject constructor(application: Application) {


    //======================================================================================================
    // Prefrance Constants
    private val PREF_NAME = "INTEGITI_TASK"
    private val FAV_LIST = "FAV_LIST"

    //================================================================================================================================

    var pref: SharedPreferences
    var editor: SharedPreferences.Editor

    // shared pref mode  PRIVATE
    var PRIVATE_MODE = 0



    private var preferences: SharedPreferences? = null

    fun instance(context: Context): SharedPreferences? {
        if (preferences == null) {
            preferences = context.getSharedPreferences(PREF_NAME, 0)
        }
        return preferences
    }


    fun saveFavList(list: ArrayList<ResultsItem>) {
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(FAV_LIST, json)
        editor.apply()
    }

    fun getFavList(): ArrayList<ResultsItem?>? {
        val gson = Gson()
        val json: String? = pref.getString(FAV_LIST, null)
        val type: Type = object : TypeToken<ArrayList<ResultsItem?>?>() {}.getType()
        return gson.fromJson(json, type)
    }

    init {
        pref = application.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

}
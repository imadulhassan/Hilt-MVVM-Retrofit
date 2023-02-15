package com.hiltMvvmRetrofit.ui.fragments.home

class HomeScreenState(val state: WorkingStatus, val first : Any? = null) {

}

enum class WorkingStatus {
    onItemClick,
    Loading,

}
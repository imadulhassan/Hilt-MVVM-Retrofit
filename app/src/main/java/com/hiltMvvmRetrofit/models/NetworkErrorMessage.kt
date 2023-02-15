package com.hiltMvvmRetrofit.models

import androidx.annotation.StringRes

data class NetworkErrorMessage(@StringRes val errorResId: Int? = null,
                               val errorMessage: String? = null)
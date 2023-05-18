package com.sample.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Maintainer(
    @SerializedName("github")
    val github: String
) : Parcelable
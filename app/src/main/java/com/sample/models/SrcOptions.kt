package com.sample.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class SrcOptions(
    @SerializedName("directory")
    val directory: String,
    @SerializedName("is_fanon")
    val isFanon: Int,
    @SerializedName("is_mediawiki")
    val isMediawiki: Int,
    @SerializedName("is_wikipedia")
    val isWikipedia: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("min_abstract_length")
    val minAbstractLength: String,
    @SerializedName("skip_abstract")
    val skipAbstract: Int,
    @SerializedName("skip_abstract_paren")
    val skipAbstractParen: Int,
    @SerializedName("skip_end")
    val skipEnd: String,
    @SerializedName("skip_icon")
    val skipIcon: Int,
    @SerializedName("skip_image_name")
    val skipImageName: Int,
    @SerializedName("skip_qr")
    val skipQr: String,
    @SerializedName("source_skip")
    val sourceSkip: String,
    @SerializedName("src_info")
    val srcInfo: String
) : Parcelable
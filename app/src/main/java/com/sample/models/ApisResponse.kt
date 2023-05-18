package com.sample.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sample.base.BaseResponse
import kotlinx.parcelize.Parcelize


@Parcelize
data class ApisResponse(
    @SerializedName("Abstract")
    val `abstract`: String,
    @SerializedName("AbstractSource")
    val abstractSource: String,
    @SerializedName("AbstractText")
    val abstractText: String,
    @SerializedName("AbstractURL")
    val abstractURL: String,
    @SerializedName("Answer")
    val answer: String,
    @SerializedName("AnswerType")
    val answerType: String,
    @SerializedName("Definition")
    val definition: String,
    @SerializedName("DefinitionSource")
    val definitionSource: String,
    @SerializedName("DefinitionURL")
    val definitionURL: String,
    @SerializedName("Entity")
    val entity: String,
    @SerializedName("Heading")
    val heading: String,
    @SerializedName("Image")
    val image: String,
    @SerializedName("ImageHeight")
    val imageHeight: Int,
    @SerializedName("ImageIsLogo")
    val imageIsLogo: Int,
    @SerializedName("ImageWidth")
    val imageWidth: Int,
    @SerializedName("Infobox")
    val infobox: String,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("Redirect")
    val redirect: String,
    @SerializedName("RelatedTopics")
    val relatedTopics: List<RelatedTopic>,
    @SerializedName("Type")
    val type: String
) : BaseResponse(), Parcelable
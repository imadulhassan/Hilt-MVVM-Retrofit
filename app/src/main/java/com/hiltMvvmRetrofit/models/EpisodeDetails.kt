package com.hiltMvvmRetrofit.models


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiltMvvmRetrofit.base.BaseResponse

data class EpisodeDetails(

	@field:SerializedName("air_date")
	val airDate: String? = null,

	@field:SerializedName("characters")
	val characters: List<String?>? = null,

	@field:SerializedName("created")
	val created: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("episode")
	val episode: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("url")
	val url: String? = null
) : BaseResponse(), Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.createStringArrayList(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(airDate)
		parcel.writeStringList(characters)
		parcel.writeString(created)
		parcel.writeString(name)
		parcel.writeString(episode)
		parcel.writeValue(id)
		parcel.writeString(url)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<EpisodeDetails> {
		override fun createFromParcel(parcel: Parcel): EpisodeDetails {
			return EpisodeDetails(parcel)
		}

		override fun newArray(size: Int): Array<EpisodeDetails?> {
			return arrayOfNulls(size)
		}
	}
}

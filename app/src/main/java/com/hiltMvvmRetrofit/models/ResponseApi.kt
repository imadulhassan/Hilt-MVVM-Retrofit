package com.hiltMvvmRetrofit.models


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiltMvvmRetrofit.base.BaseResponse


data class apiResponse(

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null,

	@field:SerializedName("info")
	val info: Info? = null
) :BaseResponse(),Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.createTypedArrayList(ResultsItem),
		parcel.readParcelable(Info::class.java.classLoader)
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeTypedList(results)
		parcel.writeParcelable(info, flags)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<apiResponse> {
		override fun createFromParcel(parcel: Parcel): apiResponse {
			return apiResponse(parcel)
		}

		override fun newArray(size: Int): Array<apiResponse?> {
			return arrayOfNulls(size)
		}
	}
}


data class Location(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
) :BaseResponse(), Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeString(url)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Location> {
		override fun createFromParcel(parcel: Parcel): Location {
			return Location(parcel)
		}

		override fun newArray(size: Int): Array<Location?> {
			return arrayOfNulls(size)
		}
	}
}


data class Info(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("pages")
	val pages: Int? = null,

	@field:SerializedName("prev")
	val prev: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null
) :BaseResponse(), Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(next)
		parcel.writeValue(pages)
		parcel.writeValue(count)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Info> {
		override fun createFromParcel(parcel: Parcel): Info {
			return Info(parcel)
		}

		override fun newArray(size: Int): Array<Info?> {
			return arrayOfNulls(size)
		}
	}
}


data class ResultsItem(

	@field:SerializedName("image")
	var image: String? ="",

	@field:SerializedName("gender")
	val gender: String? = "",

	@field:SerializedName("species")
	val species: String? = "",

	@field:SerializedName("created")
	val created: String? = "",

	@field:SerializedName("origin")
	val origin: Origin? = null,

	@field:SerializedName("name")
	val name: String? = "",

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("episode")
	val episode: List<String?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) :BaseResponse(), Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readParcelable(Origin::class.java.classLoader),
		parcel.readString(),
		parcel.readParcelable(Location::class.java.classLoader),
		parcel.createStringArrayList(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(image)
		parcel.writeString(gender)
		parcel.writeString(species)
		parcel.writeString(created)
		parcel.writeParcelable(origin, flags)
		parcel.writeString(name)
		parcel.writeParcelable(location, flags)
		parcel.writeStringList(episode)
		parcel.writeValue(id)
		parcel.writeString(type)
		parcel.writeString(url)
		parcel.writeString(status)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ResultsItem> {
		override fun createFromParcel(parcel: Parcel): ResultsItem {
			return ResultsItem(parcel)
		}

		override fun newArray(size: Int): Array<ResultsItem?> {
			return arrayOfNulls(size)
		}
	}
}


data class Origin(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
) : BaseResponse(),Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeString(url)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Origin> {
		override fun createFromParcel(parcel: Parcel): Origin {
			return Origin(parcel)
		}

		override fun newArray(size: Int): Array<Origin?> {
			return arrayOfNulls(size)
		}
	}
}

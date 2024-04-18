package com.amit.creativegalileotest.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class CustomerResponse(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("customers")
	val customers: ArrayList<CustomersItem> = ArrayList()
) : Parcelable

@Parcelize
data class CustomersItem(

	@field:SerializedName("cgId")
	val cgId: Int? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("recordStatus")
	val recordStatus: Boolean? = null,

	@field:SerializedName("dialCode")
	val dialCode: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mobile")
	val mobile: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
) : Parcelable

package me.yasinarslan.githubapi.domain

import com.google.gson.annotations.SerializedName

data class Repository(
	@SerializedName("id") val id: Int,
	@SerializedName("name") val name: String,
	@SerializedName("fullName") val fullName: String,
	@SerializedName("description") val description: String
)

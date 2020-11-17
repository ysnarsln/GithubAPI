package me.yasinarslan.githubapi.common

import com.google.gson.annotations.SerializedName

data class Error(@SerializedName("message") val message: String)

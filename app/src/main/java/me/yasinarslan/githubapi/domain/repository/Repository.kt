package me.yasinarslan.githubapi.domain.repository

import com.google.gson.annotations.SerializedName

data class Repository(
	@SerializedName("id") val id: Int,
	@SerializedName("name") val repositoryName: String,
	@SerializedName("stargazers_count") val starCount: Int,
	@SerializedName("open_issues_count") val openIssuesCount: Int,
	@SerializedName("owner") val owner: Owner
)

data class Owner(
	@SerializedName("login") val username: String,
	@SerializedName("avatar_url") val avatar: String
)
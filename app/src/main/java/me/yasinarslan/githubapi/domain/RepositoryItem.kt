package me.yasinarslan.githubapi.domain

data class RepositoryItem(
	val id: Int,
	val repositoryName: String,
	val starCount: Int,
	val openIssuesCount: Int,
	val ownerName: String,
	val ownerAvatarUrl: String,
	val isFavorite: Boolean
)
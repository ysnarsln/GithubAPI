package me.yasinarslan.githubapi.domain.repository

data class RepositoryItem(
	val id: Int,
	val repositoryName: String,
	val starCount: Int,
	val openIssuesCount: Int,
	val ownerName: String,
	val ownerAvatarUrl: String,
	var isFavorite: Boolean
)
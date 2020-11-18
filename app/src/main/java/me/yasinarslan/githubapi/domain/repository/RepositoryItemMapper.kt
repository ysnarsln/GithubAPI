package me.yasinarslan.githubapi.domain.repository

object RepositoryItemMapper {
	fun get(repository: Repository, isFavorite: Boolean): RepositoryItem {
		return RepositoryItem(
			repository.id,
			repository.repositoryName,
			repository.starCount,
			repository.openIssuesCount,
			repository.owner.username,
			repository.owner.avatar,
			isFavorite
		)
	}
}
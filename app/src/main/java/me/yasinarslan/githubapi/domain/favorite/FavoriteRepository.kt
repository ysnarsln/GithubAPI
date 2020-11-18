package me.yasinarslan.githubapi.domain.favorite

interface FavoriteRepository {
	suspend fun listFavorites(): List<Int>

	suspend fun updateFavorites(id: Int)
}
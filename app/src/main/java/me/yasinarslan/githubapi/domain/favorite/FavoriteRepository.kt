package me.yasinarslan.githubapi.domain.favorite

interface FavoriteRepository {
	fun listFavorites(): List<Int>

	fun updateFavorites(id: Int)
}
package me.yasinarslan.githubapi.data.favorite

import me.yasinarslan.githubapi.domain.favorite.FavoriteRepository

class FavoriteRepositoryImpl : FavoriteRepository {
	private val favoriteList = mutableListOf<Int>()

	override fun listFavorites(): List<Int> = favoriteList

	override fun updateFavorites(id: Int) {
		if (favoriteList.contains(id)) {
			favoriteList.remove(id)
		} else {
			favoriteList.add(id)
		}

		// todo persist favoriteList
	}
}

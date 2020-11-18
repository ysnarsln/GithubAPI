package me.yasinarslan.githubapi.data.favorite

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.yasinarslan.githubapi.domain.favorite.FavoriteRepository

class FavoriteRepositoryImpl(private val favoriteDataStore: FavoriteDataStore) : FavoriteRepository {
	private var favoriteList = mutableListOf<Int>()

	override suspend fun listFavorites(): List<Int> {
		if (favoriteList.isNullOrEmpty()) {
			val json = favoriteDataStore.getFavorites()
			val mutableListType = object : TypeToken<MutableList<Int>>() {}.type
			try {
				favoriteList = Gson().fromJson(json, mutableListType)
			} catch (e: Exception) {
			}
		}
		return favoriteList
	}

	override suspend fun updateFavorites(id: Int) {
		if (favoriteList.contains(id)) {
			favoriteList.remove(id)
		} else {
			favoriteList.add(id)
		}

		val json = Gson().toJson(favoriteList)
		favoriteDataStore.save(json)
	}
}

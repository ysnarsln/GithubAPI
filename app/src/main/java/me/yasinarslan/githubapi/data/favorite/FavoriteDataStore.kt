package me.yasinarslan.githubapi.data.favorite

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class FavoriteDataStore(private val dataStore: DataStore<Preferences>) {
	companion object {
		val FAVORITES_ID_LIST = stringPreferencesKey(name = "favorites_id_list")
	}

	suspend fun getFavorites(): String {
		return dataStore.data.map { it[FAVORITES_ID_LIST] ?: "" }.first()
	}

	suspend fun save(favoritesJson: String) {
		dataStore.edit {
			it[FAVORITES_ID_LIST] = favoritesJson
		}
	}
}
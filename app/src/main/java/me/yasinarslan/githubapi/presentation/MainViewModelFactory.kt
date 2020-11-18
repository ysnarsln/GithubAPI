package me.yasinarslan.githubapi.presentation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.yasinarslan.githubapi.common.ResourceProvider
import me.yasinarslan.githubapi.data.favorite.FavoriteDataStore
import me.yasinarslan.githubapi.data.favorite.FavoriteRepositoryImpl
import me.yasinarslan.githubapi.data.github.GithubRepositoryImpl
import me.yasinarslan.githubapi.domain.favorite.ListFavoritesUseCase
import me.yasinarslan.githubapi.domain.favorite.UpdateFavoritesUseCase
import me.yasinarslan.githubapi.domain.repository.ListRepositoriesUseCase

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {
	private val resourceProvider = ResourceProvider(context)

	private val dataStore: DataStore<Preferences> = context.createDataStore(name = "favorites")
	private val favoriteDataStore = FavoriteDataStore(dataStore)
	private val favoriteRepository = FavoriteRepositoryImpl(favoriteDataStore)
	private val listFavoritesUseCase = ListFavoritesUseCase(favoriteRepository)
	private val githubRepository = GithubRepositoryImpl()

	private val listRepositoriesUseCase = ListRepositoriesUseCase(githubRepository, listFavoritesUseCase)
	private val updateFavoritesUseCase = UpdateFavoritesUseCase(favoriteRepository)

	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
			return MainViewModel(resourceProvider, listRepositoriesUseCase, updateFavoritesUseCase) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}
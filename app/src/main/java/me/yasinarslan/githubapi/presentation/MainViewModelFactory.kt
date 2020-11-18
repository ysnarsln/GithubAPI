package me.yasinarslan.githubapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.yasinarslan.githubapi.data.favorite.FavoriteRepositoryImpl
import me.yasinarslan.githubapi.data.github.GithubRepositoryImpl
import me.yasinarslan.githubapi.domain.favorite.ListFavoritesUseCase
import me.yasinarslan.githubapi.domain.favorite.UpdateFavoritesUseCase
import me.yasinarslan.githubapi.domain.repository.ListRepositoriesUseCase

class MainViewModelFactory : ViewModelProvider.Factory {
	private val favoriteRepository = FavoriteRepositoryImpl()
	private val listFavoritesUseCase = ListFavoritesUseCase(favoriteRepository)
	private val githubRepository = GithubRepositoryImpl()

	private val listRepositoriesUseCase = ListRepositoriesUseCase(githubRepository, listFavoritesUseCase)
	private val updateFavoritesUseCase = UpdateFavoritesUseCase(favoriteRepository)

	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
			return MainViewModel(listRepositoriesUseCase, updateFavoritesUseCase) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}
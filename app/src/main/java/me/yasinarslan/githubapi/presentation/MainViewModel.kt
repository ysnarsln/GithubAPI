package me.yasinarslan.githubapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.yasinarslan.githubapi.R
import me.yasinarslan.githubapi.common.ResourceProvider
import me.yasinarslan.githubapi.common.Result
import me.yasinarslan.githubapi.domain.favorite.UpdateFavoritesUseCase
import me.yasinarslan.githubapi.domain.repository.ListRepositoriesUseCase
import me.yasinarslan.githubapi.domain.repository.RepositoryItem

class MainViewModel(
	private val resourceProvider: ResourceProvider,
	private val listRepositoriesUseCase: ListRepositoriesUseCase,
	private val updateFavoritesUseCase: UpdateFavoritesUseCase
) : ViewModel() {
	val searchText = MutableLiveData<String>()

	private val repositoryList = MutableLiveData<List<RepositoryItem>>()
	private val progressVisibility = MutableLiveData<Boolean>()
	private val message = MutableLiveData<String>()

	private var selectedPosition: Int = -1

	fun search() {
		message.value = ""
		repositoryList.value = mutableListOf()
		viewModelScope.launch {
			val search = searchText.value
			if (search.isNullOrBlank()) {
				message.value = resourceProvider.getString(R.string.warning_search_text_empty)
				return@launch
			}

			progressVisibility.value = true
			val param = ListRepositoriesUseCase.Param(search)
			val result = listRepositoriesUseCase(param)
			progressVisibility.value = false

			when (result) {
				is Result.Success -> repositoryList.value = result.data
				is Result.Failure -> message.value = result.error.message
			}
		}
	}

	fun setSelectedPosition(position: Int) {
		selectedPosition = position
	}

	fun updateFavoriteState() {
		val item = getSelectedRepositoryItem()
		viewModelScope.launch {
			val param = UpdateFavoritesUseCase.Param(item.id)
			updateFavoritesUseCase(param)
		}
		item.isFavorite = !item.isFavorite
	}

	fun getSelectedRepositoryItem(): RepositoryItem {
		return repositoryList.value!![selectedPosition]
	}

	fun getStarCountText(): String {
		return resourceProvider.getString(R.string.star_count, getSelectedRepositoryItem().starCount)
	}

	fun getOpenIssuesText(): String {
		return resourceProvider.getString(R.string.open_issues, getSelectedRepositoryItem().openIssuesCount)
	}

	fun getRepositoryList(): LiveData<List<RepositoryItem>> {
		return repositoryList
	}

	fun getProgressVisibility(): LiveData<Boolean> {
		return progressVisibility
	}

	fun getMessage(): LiveData<String> {
		return message
	}
}
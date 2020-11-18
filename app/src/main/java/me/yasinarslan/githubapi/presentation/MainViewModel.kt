package me.yasinarslan.githubapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.yasinarslan.githubapi.common.Result
import me.yasinarslan.githubapi.domain.ListRepositoriesUseCase
import me.yasinarslan.githubapi.domain.RepositoryItem

class MainViewModel(private val listRepositoriesUseCase: ListRepositoriesUseCase) : ViewModel() {
	val searchText = MutableLiveData<String>()

	private val repositoryList = MutableLiveData<List<RepositoryItem>>()
	private val progressVisibility = MutableLiveData<Boolean>()
	private val message = MutableLiveData<String>()

	fun search() {
		message.value = ""
		repositoryList.value = mutableListOf()
		viewModelScope.launch {
			val search = searchText.value
			if (search.isNullOrBlank()) {
				message.value = "Search text cannot be empty."
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
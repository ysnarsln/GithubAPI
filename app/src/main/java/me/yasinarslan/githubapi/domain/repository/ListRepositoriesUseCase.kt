package me.yasinarslan.githubapi.domain.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import me.yasinarslan.githubapi.common.Error
import me.yasinarslan.githubapi.common.Result
import me.yasinarslan.githubapi.domain.UseCase
import me.yasinarslan.githubapi.domain.favorite.ListFavoritesUseCase

class ListRepositoriesUseCase(
	private val githubRepository: GithubRepository,
	private val listFavoritesUseCase: ListFavoritesUseCase,
	coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCase<ListRepositoriesUseCase.Param, List<RepositoryItem>>(coroutineDispatcher) {

	override suspend fun execute(param: Param): Result<List<RepositoryItem>> {
		return try {
			val result = githubRepository.listRepositories(param.username)

			when (result) {
				is Result.Success -> {
					val favoritesList = listFavoritesUseCase(Unit) as Result.Success
					
					val list = result.data.map {
						RepositoryItemMapper.get(it, favoritesList.data.contains(it.id))
					}
					Result.Success(list)
				}
				is Result.Failure -> result
			}
		} catch (e: Exception) {
			Result.Failure(Error(e.message!!))
		}
	}

	data class Param(val username: String)
}
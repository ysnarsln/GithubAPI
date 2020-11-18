package me.yasinarslan.githubapi.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import me.yasinarslan.githubapi.common.Error
import me.yasinarslan.githubapi.common.Result

class ListRepositoriesUseCase(
	private val githubRepository: GithubRepository,
	coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCase<ListRepositoriesUseCase.Param, List<RepositoryItem>>(coroutineDispatcher) {

	override suspend fun execute(param: Param): Result<List<RepositoryItem>> {
		return try {
			val result = githubRepository.listRepositories(param.username)

			when (result) {
				is Result.Success -> {
					val list = result.data.map {
						RepositoryItemMapper.get(it, false)
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
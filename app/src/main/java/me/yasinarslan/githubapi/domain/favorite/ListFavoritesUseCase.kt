package me.yasinarslan.githubapi.domain.favorite

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import me.yasinarslan.githubapi.common.Result
import me.yasinarslan.githubapi.domain.UseCase

class ListFavoritesUseCase(
	private val favoriteRepository: FavoriteRepository,
	coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCase<Unit, List<Int>>(coroutineDispatcher) {

	override suspend fun execute(param: Unit): Result<List<Int>> {
		return Result.Success(favoriteRepository.listFavorites())
	}
}
package me.yasinarslan.githubapi.domain.favorite

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import me.yasinarslan.githubapi.common.Result
import me.yasinarslan.githubapi.domain.UseCase

class UpdateFavoritesUseCase(
	private val favoriteRepository: FavoriteRepository,
	coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCase<UpdateFavoritesUseCase.Param, Unit>(coroutineDispatcher) {

	override suspend fun execute(param: Param): Result<Unit> {
		favoriteRepository.updateFavorites(param.id)
		return Result.Success(Unit)
	}

	data class Param(val id: Int)
}
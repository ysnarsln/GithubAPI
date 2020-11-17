package me.yasinarslan.githubapi.data

import me.yasinarslan.githubapi.common.Result
import me.yasinarslan.githubapi.domain.GithubRepository
import me.yasinarslan.githubapi.domain.Repository
import me.yasinarslan.githubapi.network.Network
import me.yasinarslan.githubapi.network.NetworkResponse

class GithubRepositoryImpl : GithubRepository {

	override suspend fun listRepositories(user: String): Result<List<Repository>> {
		val service = Network.buildService(GithubService::class.java)

		val result = service.listRepositories(user)

		return when (result) {
			is NetworkResponse.Success -> Result.Success(result.body)
			is NetworkResponse.Failure -> Result.Failure(result.error)
		}
	}

}
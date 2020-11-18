package me.yasinarslan.githubapi.domain.repository

import me.yasinarslan.githubapi.common.Result

interface GithubRepository {
	suspend fun listRepositories(user: String): Result<List<Repository>>
}
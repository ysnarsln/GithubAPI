package me.yasinarslan.githubapi.data

import me.yasinarslan.githubapi.domain.Repository
import me.yasinarslan.githubapi.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
	@GET("users/{user}/repos")
	suspend fun listRepositories(@Path("user") user: String): NetworkResponse<List<Repository>>
}
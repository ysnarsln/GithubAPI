package me.yasinarslan.githubapi.network

import me.yasinarslan.githubapi.common.Error

sealed class NetworkResponse<T> {
	data class Success<T>(val body: T) : NetworkResponse<T>()
	data class Failure<T>(val error: Error) : NetworkResponse<T>()
}
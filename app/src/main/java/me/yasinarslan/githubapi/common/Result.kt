package me.yasinarslan.githubapi.common

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {

	data class Success<out T>(val data: T) : Result<T>()
	data class Failure(val error: Error) : Result<Nothing>()

	override fun toString(): String {
		return when (this) {
			is Success<*> -> "Success[data=$data]"
			is Failure -> "Error[error=$error]"
		}
	}
}

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

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
	get() = this is Result.Success && data != null

fun <T> Result<T>.successOr(fallback: T): T {
	return (this as? Result.Success<T>)?.data ?: fallback
}

val <T> Result<T>.data: T?
	get() = (this as? Result.Success)?.data

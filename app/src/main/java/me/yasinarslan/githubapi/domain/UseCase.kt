package me.yasinarslan.githubapi.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.yasinarslan.githubapi.common.Error
import me.yasinarslan.githubapi.common.Result

/**
 * Executes business logic synchronously or asynchronously using Coroutines.
 */
abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

	/** Executes the use case asynchronously and returns a [Result].
	 *
	 * @return a [Result].
	 *
	 * @param param the input parameters to run the use case with
	 */
	suspend operator fun invoke(param: P): Result<R> {
		return try {
			// Moving all use case's executions to the injected dispatcher
			// In production code, this is usually the Default dispatcher (background thread)
			// In tests, this becomes a TestCoroutineDispatcher
			withContext(coroutineDispatcher) {
				execute(param).let {
					it
				}
			}
		} catch (e: Exception) {
			println(e)
			Result.Failure(Error(e.localizedMessage!!))
		}
	}

	/**
	 * Override this to set the code to be executed.
	 */
	@Throws(RuntimeException::class)
	protected abstract suspend fun execute(param: P): Result<R>
}
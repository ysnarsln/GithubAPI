package me.yasinarslan.githubapi.network

import me.yasinarslan.githubapi.common.Error
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response

internal class NetworkResponseCall<T : Any>(
	private val delegate: Call<T>,
	private val errorConverter: Converter<ResponseBody, Error>
) : Call<NetworkResponse<T>> {

	override fun enqueue(callback: Callback<NetworkResponse<T>>) {
		return delegate.enqueue(object : Callback<T> {
			override fun onResponse(call: Call<T>, response: Response<T>) {
				val body = response.body()
				val error = response.errorBody()

				if (response.isSuccessful && body != null) {
					callback.onResponse(this@NetworkResponseCall, Response.success(NetworkResponse.Success(body)))
				} else {
					val errorBody = when {
						error == null -> null
						error.contentLength() == 0L -> null
						else -> try {
							errorConverter.convert(error)
						} catch (ex: Exception) {
							null
						}
					}

					val errorModel = if (errorBody == null) {
						Error("Something went wrong.")
					} else {
						Error(errorBody.message)
					}

					callback.onResponse(
						this@NetworkResponseCall,
						Response.success(NetworkResponse.Failure(errorModel))
					)
				}
			}

			override fun onFailure(call: Call<T>, throwable: Throwable) {
				callback.onResponse(
					this@NetworkResponseCall,
					Response.success(NetworkResponse.Failure(Error("Something went wrong.")))
				)
			}
		})
	}

	override fun isExecuted() = delegate.isExecuted

	override fun clone() = NetworkResponseCall(delegate.clone(), errorConverter)

	override fun isCanceled() = delegate.isCanceled

	override fun cancel() = delegate.cancel()

	override fun execute(): Response<NetworkResponse<T>> {
		throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
	}

	override fun request(): Request = delegate.request()

	override fun timeout(): Timeout = delegate.timeout()
}
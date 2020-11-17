package me.yasinarslan.githubapi.network

import me.yasinarslan.githubapi.common.Error
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class NetworkResponseAdapter<T : Any>(
	private val successType: Type,
	private val errorBodyConverter: Converter<ResponseBody, Error>
) : CallAdapter<T, Call<NetworkResponse<T>>> {
	override fun responseType(): Type = successType

	override fun adapt(call: Call<T>): Call<NetworkResponse<T>> {
		return NetworkResponseCall(call, errorBodyConverter)
	}
}

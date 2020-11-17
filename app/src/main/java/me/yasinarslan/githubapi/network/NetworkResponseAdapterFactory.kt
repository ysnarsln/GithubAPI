package me.yasinarslan.githubapi.network

import me.yasinarslan.githubapi.common.Error
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory : CallAdapter.Factory() {

	override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
		// suspend functions wrap the response type in `Call`
		if (Call::class.java != getRawType(returnType)) {
			return null
		}
		check(returnType is ParameterizedType) {
			"return type must be parameterized as Call<NetworkResponse<<Foo>> or Call<NetworkResponse<out Foo>>"
		}
		val responseType = getParameterUpperBound(0, returnType)
		if (getRawType(responseType) != NetworkResponse::class.java) {
			return null
		}
		check(responseType is ParameterizedType) { "Response must be parameterized as NetworkResponse<Foo> or NetworkResponse<out Foo>" }
		val successBodyType = getParameterUpperBound(0, responseType)
		val errorBodyConverter = retrofit.nextResponseBodyConverter<Error>(null, Error::class.java, annotations)
		return NetworkResponseAdapter<Any>(successBodyType, errorBodyConverter)
	}
}

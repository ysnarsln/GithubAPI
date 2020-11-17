package me.yasinarslan.githubapi.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
	private val okHttpClient =
		OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()

	private val retrofit = Retrofit.Builder()
		.client(okHttpClient)
		.baseUrl("https://api.github.com/")
		.addConverterFactory(GsonConverterFactory.create())
		.build()

	fun <T> buildService(service: Class<T>): T {
		return retrofit.create(service)
	}
}
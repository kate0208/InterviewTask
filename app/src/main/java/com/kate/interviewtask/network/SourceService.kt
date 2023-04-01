package com.kate.interviewtask.network

import com.kate.interviewtask.model.SourceModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com"

private val interceptor = HttpLoggingInterceptor().apply {
//    setLevel(HttpLoggingInterceptor.Level.BODY)
}
private val client = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface SourceService {
    @GET("robert0ng/NasaDataSet/main/apod.json")
    suspend fun getSource(): List<SourceModel>

}

object SourceApi {
    val sourceService: SourceService by lazy { retrofit.create(SourceService::class.java) }
}
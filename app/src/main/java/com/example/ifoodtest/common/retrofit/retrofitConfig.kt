package com.example.ifoodtest.common.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import com.example.ifoodtest.BuildConfig

const val CONNECTION_TIMEOUT = 60000L

internal fun provideOkHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)

    val interceptor = Interceptor { chain ->
        val builder = chain.request()
            .newBuilder()
        val request = builder.build()
        chain.proceed(request)
    }

    client.addInterceptor(interceptor)

    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(logging)
    }

    return client.build()
}

internal fun createMoshi() = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

internal fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl(BuildConfig.SERVER_URL)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(MoshiConverterFactory.create(createMoshi()))
    .client(okHttpClient)
    .build()

internal inline fun <reified T> createApi(retrofit: Retrofit) = retrofit.create(T::class.java)

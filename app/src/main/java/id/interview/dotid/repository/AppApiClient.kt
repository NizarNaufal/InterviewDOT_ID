package id.interview.dotid.repository

import id.interview.dotid.BuildConfig
import id.interview.dotid.repository.api.MainApi
import id.interview.dotid.support.baseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object AppApiClient {
    private val log = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }
    private val client =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) addInterceptor(log)
                .connectTimeout(5,TimeUnit.MINUTES)
                .readTimeout(5,TimeUnit.MINUTES)
                .build()
        }

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
        .build()

    fun mainClient(): MainApi = retrofit.create(
        MainApi::class.java
    )
}
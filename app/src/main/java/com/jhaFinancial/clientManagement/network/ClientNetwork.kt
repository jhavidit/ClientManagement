package com.jhaFinancial.clientManagement.network

import android.content.Context
import com.jhaFinancial.clientManagement.utils.InternetConnectivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ClientNetwork {

    private const val BASE_URL = "https://client-management-application.herokuapp.com/"
    private const val cacheSize = (5 * 1024 * 1024).toLong()
    fun getClient(context: Context): ClientNetworkInterface {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val myCache = Cache(context.cacheDir, cacheSize)
        val httpClient = OkHttpClient.Builder()
            .cache(myCache)
            .readTimeout(600, TimeUnit.SECONDS)
            .connectTimeout(600, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (InternetConnectivity.isNetworkAvailable(context)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
            .build()


        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(httpClient)
            .build()


        val retrofitService: ClientNetworkInterface by lazy {
            retrofit.create(ClientNetworkInterface::class.java)
        }
        return retrofitService
    }
}
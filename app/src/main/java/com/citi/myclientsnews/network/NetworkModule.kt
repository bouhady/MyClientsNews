package com.citi.myclientsnews.network

import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by yb34982 on 21/03/2018.
 */
@Module
class NetworkModule {
    private val BASE_URL = "https://b2bce8jh8a.execute-api.us-west-2.amazonaws.com/"
    private val CONNECTION_TIMEOUT_SEC = 30L


    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return createRetrofit(BASE_URL)
    }

    @Singleton
    @Provides
    fun providesMyClientsNewApi(): MyClientsNewsApiService {
        return createRetrofit(BASE_URL)
            .create(MyClientsNewsApiService::class.java)
    }

    private fun createRetrofit(baseUrl: String): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)

        clientBuilder.addInterceptor(interceptor)
//        clientBuilder.addInterceptor(EncodingInterceptor())
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(clientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

class EncodingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val modifiedBody = ResponseBody.create(mediaType, response.body()?.bytes())
        val modifiedResponse = response.newBuilder()
            .body(modifiedBody)
            .build()

        return modifiedResponse
    }
}
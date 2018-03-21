package com.citi.myclientsnews.network

import com.citi.myclientsnews.model.NewDataClass
import com.citi.myclientsnews.model.TrumpResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by yb34982 on 21/03/2018.
 */
interface MyClientsNewsApiService {
    @GET("phones/{phoneNum}.json")
    fun getRegistrationState(@Path("phoneNum") phoneNum: String): Single<String?>

    @GET("api/bot")
    fun askTrump(
        @Query("input") request: String
    ): Observable<TrumpResponse>

    @GET("DEV/api/news")
    fun getNews(): Observable<NewDataClass>
}
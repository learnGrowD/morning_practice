package com.will_d.ex06mvvm

import android.database.Observable
import io.reactivex.rxjava3.internal.schedulers.RxThreadFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface NaverAPI {
    @GET("v1/saerch/news.json")
    fun getSearchNews(
        @Query("query") query : String,
        @Query("display") display : Int? = null,
        @Query("start") start : Int? = null
    ) : Observable<ResultGetSearchNews>

    companion object {
        private const val BASE_URL_NAVER_API = "https://openapi.naver.com/"
        private const val CLIENT_ID = "아이디 입력"
        private const val CLIENT_SECRET = "비밀번호 입력"

        fun create(): NaverAPI {
            val headerInterceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("X-Naver-Client-Id", CLIENT_ID)
                    .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
                    .build()
                return@Interceptor it.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL_NAVER_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NaverAPI::class.java)
        }
    }
}
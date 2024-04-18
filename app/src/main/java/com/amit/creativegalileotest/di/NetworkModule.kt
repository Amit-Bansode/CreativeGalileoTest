package com.amit.creativegalileotest.di

import com.amit.creativegalileotest.retrofit.CustomerAPI
import com.amit.creativegalileotest.utils.Constants.BASE_URL
import com.amit.creativegalileotest.utils.Constants.BEARER
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request =
                    chain.request().newBuilder().addHeader("Authorization", BEARER).build()
                return chain.proceed(request)
            }
        })

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Singleton
    @Provides
    fun getCustomerAPI(retrofit: Retrofit): CustomerAPI {
        return retrofit.create(CustomerAPI::class.java)
    }
}
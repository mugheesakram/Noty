package com.task.noty.common.api.client

import com.task.noty.common.constants.BaseEndPoints
import com.task.noty.common.api.apikeys.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit





private val REQUEST_TIMEOUT: Long = 60

class ApiClient {

    companion object {
        fun newInstance() = ApiClient()
    }

    /*   companion object {
           private var apiClient: ApiClient? = null

           val newInstance: ApiClient
               get() {
                   if (apiClient == null) {
                       apiClient = ApiClient()
                   }
                   return apiClient as ApiClient
               }
       }*/


    var retrofit: Retrofit? = null


    var okHttpClient: OkHttpClient? = null

    fun getClient(): Retrofit? {

        if (okHttpClient == null) initOkHttp()


        if (retrofit == null) {


            retrofit = Retrofit.Builder()
                .baseUrl(BaseEndPoints.DOMAIN)
                .client(okHttpClient!!)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit;

    }

    private fun initOkHttp() {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder();

            requestBuilder.addHeader(ACCEPT, APPLICATION_JSON)
            requestBuilder.addHeader(REQUEST_TYPE, ANDROID)
            requestBuilder.addHeader(CONTENT_TYPE, APPLICATION_JSON)


            val request = requestBuilder.build()
            chain.proceed(request)

        }

        okHttpClient = httpClient.build();
    }


}

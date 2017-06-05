package com.tapdevs.kotlin.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by  Jan Shair on 31/01/2017.
 */

class RetrofitHelper {

    val  serverUrl: String ="https://api.github.com/"

    fun newApiCalls(): ApiCalls {

        val apiInterface = Retrofit.Builder()
                .baseUrl(serverUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiCalls::class.java)

        return apiInterface
    }
}

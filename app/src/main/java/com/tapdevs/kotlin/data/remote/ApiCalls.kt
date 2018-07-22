package com.tapdevs.kotlin.data.remote

import com.tapdevs.kotlin.models.ContainerObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by  Jan Shair on 31/01/2017.
 */

interface ApiCalls {

    @get:GET("master/data.json")
    val fruits: Observable<ContainerObject>

    @GET("?event=load")
    fun loadEvent(@Query("data") timeInMillis: Long ) : Observable<String>

    @GET("?event=display")
    fun displayEvent(@Query("data") timeInMillis: Long ) : Observable<String>

    @GET("?event=error")
    fun errorEvent(@Query("data") exceptionStr: String ) : Observable<String>
}

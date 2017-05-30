package com.tapdevs.kotlin.data.remote

import com.tapdevs.kotlin.models.User
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by  Jan Shair on 31/01/2017.
 */

interface ApiCalls {

    @get:GET("users")
    val users: Observable<List<User>>

}

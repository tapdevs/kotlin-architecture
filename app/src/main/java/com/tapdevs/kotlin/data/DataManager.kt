package com.tapdevs.kotlin.data


import com.tapdevs.kotlin.data.remote.ApiCalls
import com.tapdevs.kotlin.models.User

import javax.inject.Inject

import io.reactivex.Scheduler

/**
 * Created by  Jan Shair on 31/01/2017.
 */

class DataManager(@Inject
                  protected var apiCalls: ApiCalls,
                  subscribeScheduler: Scheduler) {
    @Inject var scheduler: Scheduler
        protected set


    init {
        scheduler = subscribeScheduler
    }

    val userList: io.reactivex.Observable<List<User>>
        get() = apiCalls.users


}

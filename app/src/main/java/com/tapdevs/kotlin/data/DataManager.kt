package com.tapdevs.kotlin.data


import com.tapdevs.kotlin.data.remote.ApiCalls
import com.tapdevs.kotlin.models.User


import io.reactivex.Scheduler

/**
 * Created by  Jan Shair on 31/01/2017.
 */

class DataManager(
                  var apiCalls: ApiCalls,
                  subscribeScheduler: Scheduler) {
     var scheduler: Scheduler
        protected set


    init {
        scheduler = subscribeScheduler
    }

    val userList: io.reactivex.Observable<List<User>>
        get() = apiCalls.users


}

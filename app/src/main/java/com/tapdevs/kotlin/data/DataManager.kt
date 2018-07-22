package com.tapdevs.kotlin.data


import com.tapdevs.kotlin.data.remote.ApiCalls
import com.tapdevs.kotlin.models.ContainerObject
import io.reactivex.Observable


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


    val fruitList: io.reactivex.Observable<ContainerObject>
        get() = apiCalls.fruits


    fun loadEvent() : Observable<String> =  apiCalls.loadEvent(System.currentTimeMillis())

    fun displayEvent() : Observable<String> =  apiCalls.displayEvent(System.currentTimeMillis())

    fun errorEvent(errorText : String)  : Observable<String> =  apiCalls.errorEvent(errorText)
}

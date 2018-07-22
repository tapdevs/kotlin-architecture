package com.tapdevs.kotlin.injections.binding

import android.app.Application
import com.tapdevs.kotlin.injections.components.AppComponent
import com.tapdevs.kotlin.injections.modules.AppModule
import com.tapdevs.kotlin.injections.components.DaggerAppComponent


/**
 * Created by  Jan Shair on 04/06/2017.
 */

object DIBinder {

//    fun getUserComponent(): FruitsComponent? {
//        val usersComponent = DaggerFruitsComponent.builder.usersModule(FruitsModule()).build()
//        return usersComponent
//    }
    fun getAppComponent(application: Application): AppComponent? {
            return DaggerAppComponent
                    .create()

    }
}

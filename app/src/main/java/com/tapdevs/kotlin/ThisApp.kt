package com.tapdevs.kotlin

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.tapdevs.abstractions.utils.CrashlyticsTree
import com.tapdevs.kotlin.injections.components.DaggerAppComponent
import com.tapdevs.kotlin.injections.modules.AppModule
import io.realm.Realm
import timber.log.Timber


/**
 * Created by  Jan Shair on 28/05/2017.
 */

class ThisApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .create()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        else
            Timber.plant(CrashlyticsTree())

        Realm.init(this)

    }



}

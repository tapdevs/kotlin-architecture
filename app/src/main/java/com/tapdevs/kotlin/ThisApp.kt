package com.tapdevs.kotlin

import android.app.Application
import android.util.Log
import com.squareup.leakcanary.LeakCanary
import com.tapdevs.abstractions.utils.CrashlyticsTree
import com.tapdevs.kotlin.injections.modules.UsersModule
import io.realm.Realm
import timber.log.Timber


/**
 * Created by  Jan Shair on 28/05/2017.
 */

class ThisApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        else
            Timber.plant(CrashlyticsTree())

        Realm.init(this)
    }



}

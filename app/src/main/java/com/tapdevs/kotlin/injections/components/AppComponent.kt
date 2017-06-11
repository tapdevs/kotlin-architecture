package com.tapdevs.kotlin.injections.components

import android.app.Application
import com.tapdevs.kotlin.injections.modules.AppModule
import dagger.Component
import xyz.ivankocijan.kotlinexample.dagger.setup.module.GsonConverterModule
import xyz.ivankocijan.kotlinexample.dagger.setup.module.GsonModule
import javax.inject.Singleton

/**
 * Created by  Jan Shair on 05/06/2017.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, GsonConverterModule::class,
        GsonModule::class))
interface AppComponent {
    fun inject(app: Application)
}
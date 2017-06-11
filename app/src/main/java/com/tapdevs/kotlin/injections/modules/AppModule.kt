package com.tapdevs.kotlin.injections.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by  Jan Shair on 05/06/2017.
 */

@Module
class AppModule(private val app: Application) {
    @Provides @Singleton
    fun provideApplication() = app
}
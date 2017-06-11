package xyz.ivankocijan.kotlinexample.dagger.setup.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by  Jan Shair on 04/06/2017.
 */

@Module
class GsonModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

}
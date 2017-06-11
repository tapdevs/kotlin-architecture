package xyz.ivankocijan.kotlinexample.dagger.setup.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



/**
 * Created by  Jan Shair on 04/06/2017.
 */
@Module
class GsonConverterModule {

    @Provides
    @Singleton
    fun provideConverter(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

}
package com.tapdevs.kotlin.injections.modules

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tapdevs.abstractions.utils.SharedPreferenceUtil
import com.tapdevs.kotlin.data.DataManager
import com.tapdevs.kotlin.data.RealmDataManager
import com.tapdevs.kotlin.data.remote.ApiCalls
import com.tapdevs.kotlin.data.remote.RetrofitHelper
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by  Jan Shair on 28/05/2017.
 */

@Module
class UsersModule// Constructor needs one parameter to instantiate.
(internal var mBaseUrl: String) {

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    internal fun providesSharedPreferences(application: Application):
            // Application reference must come from ApplicationModule.class
            SharedPreferenceUtil {
        return SharedPreferenceUtil(application)
    }

    @Provides
    @Singleton
    internal fun providesRealm(application: Application):
            // Application reference must come from ApplicationModule.class
            RealmDataManager {
        return RealmDataManager()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(application.cacheDir, cacheSize.toLong())
        return cache
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder().cache(cache).build()
    }

    @Provides
    @Singleton
    internal fun provideApiInterface(application: Application): ApiCalls {

        return RetrofitHelper().newApiCalls()
    }

    @Provides
    @Singleton
    internal fun provideDataManager(application: Application): DataManager {
        return DataManager(provideApiInterface(application), provideSubscribeScheduler())
    }

    @Provides
    @Singleton
    internal fun provideSubscribeScheduler(): Scheduler {
        return Schedulers.io()
    }
}

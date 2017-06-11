package com.tapdevs.kotlin.injections.modules

import com.tapdevs.abstractions.utils.SharedPreferenceUtil
import com.tapdevs.kotlin.data.DataManager
import com.tapdevs.kotlin.data.RealmDataManager
import com.tapdevs.kotlin.data.remote.ApiCalls
import com.tapdevs.kotlin.data.remote.RetrofitHelper
import com.tapdevs.kotlin.injections.scope.ActivityScope
import com.tapdevs.kotlin.views.fragments.UsersFragment
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Created by  Jan Shair on 28/05/2017.
 */
//
@Module
class UsersModule
 {
//
    @Provides
    @ActivityScope
    fun providesSharedPreferences(application: UsersFragment): SharedPreferenceUtil = SharedPreferenceUtil(application.activity.application)

    @Provides
    @ActivityScope
    fun providesRealm(): RealmDataManager = RealmDataManager()


     @Provides
     @ActivityScope
     fun providesCompositeDisposible(): CompositeDisposable = CompositeDisposable()


     fun provideApiInterface(): ApiCalls =  RetrofitHelper().newApiCalls()




    @Provides
    @ActivityScope
    fun provideDataManager(): DataManager {
        return DataManager(provideApiInterface(), provideSubscribeScheduler())
    }

     fun provideSubscribeScheduler(): Scheduler = Schedulers.io()

}

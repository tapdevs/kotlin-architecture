package com.tapdevs.kotlin.injections.modules

import com.tapdevs.abstractions.utils.SharedPreferenceUtil
import com.tapdevs.kotlin.data.DataManager
import com.tapdevs.kotlin.data.RealmDataManager
import com.tapdevs.kotlin.data.remote.ApiCalls
import com.tapdevs.kotlin.data.remote.RetrofitHelper
import com.tapdevs.kotlin.injections.scope.ActivityScope
import com.tapdevs.kotlin.views.fragments.FruitsFragment
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by  Jan Shair on 28/05/2017.
 */
//
@Module
class FruitsModule
 {
//
    @Provides
    @ActivityScope
    fun providesSharedPreferences(application: FruitsFragment): SharedPreferenceUtil = SharedPreferenceUtil(application.activity!!.application)

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

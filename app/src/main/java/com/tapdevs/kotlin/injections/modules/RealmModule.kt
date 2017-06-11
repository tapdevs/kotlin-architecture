package com.tapdevs.kotlin.injections.modules

import com.tapdevs.kotlin.data.RealmDataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by  Jan Shair on 05/06/2017.
 */


@Module
class RealmModule
{

    @Provides
    @Singleton
    fun providesRealm(): RealmDataManager = RealmDataManager()


}

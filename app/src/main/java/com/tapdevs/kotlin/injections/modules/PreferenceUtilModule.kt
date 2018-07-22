package com.tapdevs.kotlin.injections.modules

import com.tapdevs.abstractions.utils.SharedPreferenceUtil
import com.tapdevs.kotlin.views.fragments.FruitsFragment
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by  Jan Shair on 05/06/2017.
 */

@Module
class PreferenceUtilModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(application: FruitsFragment):SharedPreferenceUtil = SharedPreferenceUtil(application.activity!!.application)
}
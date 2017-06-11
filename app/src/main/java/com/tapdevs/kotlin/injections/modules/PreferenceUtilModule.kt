package com.tapdevs.kotlin.injections.modules

import com.tapdevs.abstractions.utils.SharedPreferenceUtil
import com.tapdevs.kotlin.views.fragments.UsersFragment
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
    fun providesSharedPreferences(application: UsersFragment):SharedPreferenceUtil = SharedPreferenceUtil(application.activity.application)
}
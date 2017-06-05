package com.tapdevs.kotlin.injections.components

import com.tapdevs.kotlin.views.fragments.UsersFragment

/**
 * Created by  Jan Shair on 28/05/2017.
// */
//@Singleton
//@Component(modules = arrayOf(
//        UsersModule::class,
//        GsonConverterModule::class,
//        GsonModule::class))
interface UsersComponent {
    fun inject(usersFragment: UsersFragment)
}
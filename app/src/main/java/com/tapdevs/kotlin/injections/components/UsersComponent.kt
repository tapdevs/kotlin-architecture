package com.tapdevs.kotlin.injections.components

import com.tapdevs.kotlin.injections.modules.UsersModule
import com.tapdevs.kotlin.injections.scope.ActivityScope
import com.tapdevs.kotlin.views.fragments.UsersFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by  Jan Shair on 28/05/2017.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(
        UsersModule::class
        ))
interface UsersComponent {
    fun inject(usersFragment: UsersFragment)
}
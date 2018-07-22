package com.tapdevs.kotlin.injections.components

import com.tapdevs.kotlin.injections.modules.FruitsModule
import com.tapdevs.kotlin.injections.scope.ActivityScope
import com.tapdevs.kotlin.views.fragments.FruitsFragment
import dagger.Component

/**
 * Created by  Jan Shair on 28/05/2017.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(
        FruitsModule::class
        ))
interface FruitsComponent {
    fun inject(fruitsFragment: FruitsFragment)
}
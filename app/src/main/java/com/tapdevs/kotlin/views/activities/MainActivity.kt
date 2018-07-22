package com.tapdevs.kotlin.views.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import com.tapdevs.abstractions.views.BaseActivity
import com.tapdevs.kotlin.R
import com.tapdevs.kotlin.data.AppConstants
import com.tapdevs.kotlin.views.fragments.FruitsFragment

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(FruitsFragment())

    }

    override fun injectDependencies() {

    }


    fun setFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_content, fragment, fragment.javaClass.simpleName)
                .commit()
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_content, fragment)
                .addToBackStack(AppConstants.BROWSE_FRAGMENT_TAG)
                .commit()
    }

}

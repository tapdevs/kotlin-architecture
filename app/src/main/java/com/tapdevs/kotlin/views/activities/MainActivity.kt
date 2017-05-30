package com.tapdevs.kotlin.views.activities

import android.os.Bundle
import com.tapdevs.abstractions.views.BaseActivity
import com.tapdevs.kotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun injectDependencies() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

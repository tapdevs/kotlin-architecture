package com.tapdevs.kotlin.viewmodels

import android.databinding.BaseObservable
import android.view.View
import com.tapdevs.kotlin.models.Fruit
import com.tapdevs.kotlin.views.fragments.FruitsFragment

/**
 * Created by  Jan Shair on 29/05/2017.
 */

class FruitsViewModel(private val context: FruitsFragment) : BaseObservable() {


    fun getType(fruit: Fruit): String{
        return "Type : ${fruit.type}"
    }

    fun getPrice(fruit: Fruit): String{
        return "Price : ${fruit.price}"
    }


    fun getWeight(fruit: Fruit): String{
        return "Weight : ${fruit.weight}"
    }



}
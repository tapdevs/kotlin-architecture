package com.tapdevs.kotlin.viewmodels

import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tapdevs.kotlin.R
import com.tapdevs.kotlin.models.User
import com.tapdevs.kotlin.views.fragments.UsersFragment

/**
 * Created by  Jan Shair on 29/05/2017.
 */


class UserViewModel(private val context: UsersFragment, val user: User) : BaseObservable() {


    fun onClickView(view: View) {
//        context.browseThisUser(user)

    }

    companion object {


        @BindingAdapter("imageUrl")
        fun loadImage(imageView: ImageView, url: String) {

            Glide.with(imageView.context)
                    .load(url)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
        }
    }


}
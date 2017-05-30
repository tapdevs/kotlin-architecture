package com.tapdevs.kotlin.views.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tapdevs.kotlin.R
import com.tapdevs.kotlin.databinding.UserRowBinding
import com.tapdevs.kotlin.models.User
import com.tapdevs.kotlin.viewmodels.UserViewModel
import com.tapdevs.kotlin.views.fragments.UsersFragment

/**
 * Created by  Jan Shair on 29/05/2017.
 */

class UserAdapter(private val context: UsersFragment, private var userArrayList: List<User>?) : RecyclerView.Adapter<UserAdapter.BindingHolder>() {

    fun setItems(posts: List<User>) {
        userArrayList = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {

        val commentBinding : UserRowBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.user_row,
                parent,
                false)
        return BindingHolder(commentBinding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {

        val user = userArrayList!![position]
        val commentsHeaderBinding = holder.binding as UserRowBinding
        commentsHeaderBinding.setViewModel(UserViewModel(context, user))
    }

    override fun getItemCount(): Int {
        return userArrayList!!.size
    }

    inner class BindingHolder(binding: UserRowBinding) : RecyclerView.ViewHolder(binding.containerItem) {

        val binding: ViewDataBinding

        init {
            this.binding = binding
        }

    }

}

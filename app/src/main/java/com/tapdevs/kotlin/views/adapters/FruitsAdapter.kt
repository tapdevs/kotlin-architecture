package com.tapdevs.kotlin.views.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tapdevs.kotlin.R
import com.tapdevs.kotlin.databinding.RowFruitBinding
import com.tapdevs.kotlin.models.Fruit
import com.tapdevs.kotlin.viewmodels.FruitsViewModel
import com.tapdevs.kotlin.views.fragments.FruitsFragment

/**
 * Created by  Jan Shair on 29/05/2017.
 */

class UserAdapter(private val context: FruitsFragment, private var userArrayList: List<Fruit>?) : RecyclerView.Adapter<UserAdapter.BindingHolder>() {

    fun setItems(posts: List<Fruit>) {
        userArrayList = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {

        val commentBinding : RowFruitBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_fruit,
                parent,
                false)
        return BindingHolder(commentBinding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {

        val user = userArrayList!![position]
        val commentsHeaderBinding = holder.binding as RowFruitBinding
        commentsHeaderBinding.fruit = user
        commentsHeaderBinding.viewModel = FruitsViewModel(context)
    }

    override fun getItemCount(): Int {
        return userArrayList!!.size
    }

    inner class BindingHolder(binding: RowFruitBinding) : RecyclerView.ViewHolder(binding.containerItem) {

        val binding: ViewDataBinding

        init {
            this.binding = binding
        }

    }

}

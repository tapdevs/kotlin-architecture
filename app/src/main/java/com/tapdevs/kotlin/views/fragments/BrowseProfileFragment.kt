package com.tapdevs.kotlin.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tapdevs.abstractions.views.BaseFragment
import com.tapdevs.kotlin.R
import com.tapdevs.kotlin.data.AppConstants
import com.tapdevs.kotlin.data.RealmDataManager
import com.tapdevs.kotlin.models.User
import kotlinx.android.synthetic.main.fragment_browse_profile.*

/**
 * Created by  Jan Shair on 02/06/2017.
 */

class BrowseProfileFragment : BaseFragment(){



    // TODO: Rename and change types of parameters
    var userObject: User? = null
    val realm: RealmDataManager = RealmDataManager()


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @param userObject Parameter 1.
     * *
     * @return A new instance of fragment BrowseProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    fun newInstance(userObject: User): BrowseProfileFragment {
        val fragment = BrowseProfileFragment()
        val args = Bundle()
        args.putParcelable(AppConstants.USER_OBJECT_PARCELABLE_KEY, userObject)
        fragment.setArguments(args)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userObject = arguments?.getParcelable(AppConstants.USER_OBJECT_PARCELABLE_KEY)

    }


    override fun getBindingView(inflater: LayoutInflater, fragmentLayout: Int, container: ViewGroup, b: Boolean): View? {
        return null
    }

    override fun initialize() {
        if (realm.realm!!.isClosed()) {
            realm.initRealm()
        }
        webview?.loadUrl(userObject?.html_url)
        webview?.settings?.javaScriptEnabled = true

    }




    override val fragmentLayout: Int

        get() = R.layout.fragment_browse_profile

    override protected fun injectDependencies() {

    }


    override fun onDetach() {
        super.onDetach()
    }

}
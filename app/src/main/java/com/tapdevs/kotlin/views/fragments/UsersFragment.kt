package com.tapdevs.kotlin.views.fragments

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tapdevs.abstractions.eventHandlers.NetworkStatus
import com.tapdevs.abstractions.utils.AbstractionOrUtilsConstants
import com.tapdevs.abstractions.utils.DialogFactory
import com.tapdevs.abstractions.views.BaseFragment
import com.tapdevs.kotlin.R
import com.tapdevs.kotlin.R.id.*
import com.tapdevs.kotlin.R.layout.fragment_users
import com.tapdevs.kotlin.data.AppConstants
import com.tapdevs.kotlin.data.DataManager
import com.tapdevs.kotlin.data.RealmDataManager
import com.tapdevs.kotlin.data.remote.RetrofitHelper
import com.tapdevs.kotlin.injections.components.DaggerAppComponent
import com.tapdevs.kotlin.injections.components.DaggerUsersComponent
import com.tapdevs.kotlin.injections.modules.UsersModule
import com.tapdevs.kotlin.models.User
import com.tapdevs.kotlin.views.activities.MainActivity
import com.tapdevs.kotlin.views.adapters.UserAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.util.HalfSerializer.onComplete
import io.reactivex.internal.util.HalfSerializer.onNext
import io.reactivex.schedulers.Schedulers
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.android.synthetic.main.layout_offline.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 * Created by  Jan Shair on 28/05/2017.
 */

class UsersFragment : BaseFragment() , SwipeRefreshLayout.OnRefreshListener{


    @Inject lateinit var mCompositeDisposable: CompositeDisposable

    lateinit var mAdapter: UserAdapter

    lateinit var users: ArrayList<User>

    @Inject lateinit var mDataManager: DataManager

    @Inject lateinit var realm: RealmDataManager

    override val fragmentLayout: Int

        get() = fragment_users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        users = ArrayList<User>()
        mAdapter = UserAdapter(this, users)
    }







    override fun getBindingView(inflater: LayoutInflater, fragmentLayout: Int, container: ViewGroup, b: Boolean): View? {
        return null
    }

    override fun initialize() {
        if (realm.realm!!.isClosed()) {
            realm.initRealm()
        }
        button_try_again.setOnClickListener(this::onTryAgainClick)
        swipeContainer?.setOnRefreshListener(this)
        swipeContainer?.setColorSchemeResources(R.color.colorPrimary)
        setupRecyclerView()
        loadUsersIfNetworkConnected()
    }





    override fun injectDependencies() {
//        DIBinder.getUserComponent(activity.application)?.inject(this)
        DaggerUsersComponent.builder().appComponent(DaggerAppComponent.create()).usersModule(UsersModule()).build().inject(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
    }

    override fun onRefresh() {
        mCompositeDisposable.clear()
        mAdapter?.let { mAdapter.setItems(ArrayList<User>()) }

        loadUsersIfNetworkConnected()
    }


    fun onTryAgainClick(view: View) {
        loadUsersIfNetworkConnected()
    }

    private fun setupRecyclerView() {
        recyclerView?.let {
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.setHasFixedSize(true)
            mAdapter?.setItems(users!!)
            recyclerView.adapter = mAdapter
        }
    }


    fun onNext(value: List<User>) {
        showHideOfflineLayout(false)
        hideLoadingViews()
        realm?.saveUserObjects(value)
        handleResponse(realm?.allUsers)


    }

    override fun onDetach() {
        super.onDetach()
        realm?.realm?.close()
    }

    fun onError(e: Throwable) {
        handleError(e)
        hideLoadingViews()
        Timber.e("There was a problem loading users " + e)
        e.printStackTrace()
        DialogFactory.createSimpleOkErrorDialog(
                context,
                "There was a problem loading users " + e
        ).show()
    }

    fun onComplete() {

        hideLoadingViews()


    }

    private fun loadUsersIfNetworkConnected() {

        if (AbstractionOrUtilsConstants.networkStatus != NetworkStatus.networkStatusNotReachable) {


            mCompositeDisposable?.add(mDataManager!!.userList
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(mDataManager!!.scheduler)
                    .subscribe(this::onNext,this::onError,this::onComplete))
        } else {
            val allOfflineUsers = realm?.allUsers
            if (allOfflineUsers!!.size!! > 0) {
                handleResponse(realm?.allUsers)
            } else {
                showError(getString(R.string.noInternet))
            }

        }
    }

    private fun handleResponse(androidList: RealmResults<User>?) {
        hideLoadingViews()
        users = ArrayList<User>(androidList)
        mAdapter = UserAdapter(this, users)
        recyclerView?.adapter = mAdapter
    }

    private fun handleError(error: Throwable) {

        showError("Error " + error.localizedMessage)
    }


    private fun hideLoadingViews() {
        progressIndicator?.visibility = View.GONE
        swipeContainer?.isRefreshing = false
    }

    private fun showHideOfflineLayout(isOffline: Boolean) {
        layoutOffline?.visibility = if (isOffline) View.VISIBLE else View.GONE
        recyclerView?.visibility = if (isOffline) View.GONE else View.VISIBLE
        progressIndicator?.visibility = if (isOffline) View.GONE else View.VISIBLE
        hideLoadingViews()
    }

    private fun showError(message: String) {
        showHideOfflineLayout(true)
        errorView?.text = message
    }


    fun browseThisUser(user: User) {
        val browseProfileFragment = BrowseProfileFragment()
        val args = Bundle()
        args.putParcelable(AppConstants.USER_OBJECT_PARCELABLE_KEY, user)
        browseProfileFragment.setArguments(args)

        (activity as MainActivity).setFragment(browseProfileFragment)
    }

}


package com.tapdevs.kotlin.views.fragments

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tapdevs.abstractions.eventHandlers.NetworkStatus
import com.tapdevs.abstractions.utils.NetworkUtils
import com.tapdevs.abstractions.views.BaseFragment
import com.tapdevs.kotlin.R
import com.tapdevs.kotlin.R.layout.fragment_fruits
import com.tapdevs.kotlin.data.AppConstants
import com.tapdevs.kotlin.data.DataManager
import com.tapdevs.kotlin.data.RealmDataManager
import com.tapdevs.kotlin.injections.components.DaggerAppComponent
import com.tapdevs.kotlin.injections.components.DaggerFruitsComponent
import com.tapdevs.kotlin.injections.modules.FruitsModule
import com.tapdevs.kotlin.models.ContainerObject
import com.tapdevs.kotlin.models.Fruit
import com.tapdevs.kotlin.views.activities.MainActivity
import com.tapdevs.kotlin.views.adapters.UserAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_fruits.*
import kotlinx.android.synthetic.main.layout_offline.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 * Created by  Jan Shair on 28/05/2017.
 */

class FruitsFragment : BaseFragment() , SwipeRefreshLayout.OnRefreshListener{


    @Inject lateinit var mCompositeDisposable: CompositeDisposable

    lateinit var mAdapter: UserAdapter

    lateinit var fruits: ArrayList<Fruit>

    @Inject lateinit var mDataManager: DataManager

    @Inject lateinit var realm: RealmDataManager

    override val fragmentLayout: Int

        get() = fragment_fruits

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fruits = ArrayList<Fruit>()
        mAdapter = UserAdapter(this, fruits)
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
        DaggerFruitsComponent.builder().appComponent(DaggerAppComponent.create()).fruitsModule(FruitsModule()).build().inject(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
    }

    override fun onRefresh() {
        mCompositeDisposable.clear()
        mAdapter?.let { mAdapter.setItems(ArrayList<Fruit>()) }

        loadUsersIfNetworkConnected()
    }


    fun onTryAgainClick(view: View) {
        loadUsersIfNetworkConnected()
    }

    private fun setupRecyclerView() {
        recyclerView?.let {
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.setHasFixedSize(true)
            mAdapter.setItems(fruits)
            recyclerView.adapter = mAdapter
        }
    }




    fun onFruitsReceived(value: ContainerObject) {
        showHideOfflineLayout(false)
        hideLoadingViews()

        if(value.fruit.size > 0){
            realm.saveFruitsObjects(value.fruit)
            handleResponse(realm.fruits)

            mCompositeDisposable.add(mDataManager.displayEvent()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(mDataManager.scheduler)
                    .subscribe(this::loadResponse,this::errorEvent,this::onComplete))
        }
    }

    override fun onDetach() {
        super.onDetach()
        realm.realm?.close()
    }

    fun onError(e: Throwable) {
        handleError(e)
        hideLoadingViews()
        Timber.e("There was a problem loading fruits " + e)
        e.printStackTrace()
//        DialogFactory(context!!).createSimpleOkErrorDialog(
//                context,
//                "There was a problem loading fruits " + e
//        ).show()
    }

    fun onComplete() {

        hideLoadingViews()


    }

    private fun loadUsersIfNetworkConnected() {

        if (NetworkStatus.networkStatusNotReachable != NetworkUtils.getReachability(context!!)) {


            mCompositeDisposable.add(mDataManager.fruitList
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(mDataManager.scheduler)
                    .subscribe(this::onFruitsReceived,this::onError,this::onComplete))


            mCompositeDisposable.add(mDataManager.loadEvent()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(mDataManager.scheduler)
                    .subscribe(this::loadResponse,this::errorEvent,this::onComplete))
        } else {
            val allFruits = realm.fruits
            if (allFruits.size > 0) {
                handleResponse(realm.fruits)
            } else {
                showError(getString(R.string.noInternet))
            }

        }
    }

    fun loadResponse(value : String){

    }

    private fun handleResponse(androidList: RealmResults<Fruit>?) {
        hideLoadingViews()
        fruits = ArrayList<Fruit>(androidList)
        mAdapter = UserAdapter(this, fruits)
        recyclerView?.adapter = mAdapter
    }

    private fun handleError(error: Throwable) {


        mCompositeDisposable.add(mDataManager.errorEvent(error.localizedMessage.replace(" ","%20"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.scheduler)
                .subscribe(this::loadResponse,this::errorEvent,this::onComplete))
        showError("Error " + error.localizedMessage)
    }


    private fun errorEvent(error: Throwable) {
        Timber.d(error.message)
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



}


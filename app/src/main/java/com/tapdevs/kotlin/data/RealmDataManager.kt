package com.tapdevs.kotlin.data


import com.tapdevs.kotlin.models.User
import io.realm.Realm
import io.realm.RealmResults

/**
 * Created by  Jan Shair on 18/02/2017.
 */

class RealmDataManager {

    var realm: Realm? = null
        private set

    init {
        initRealm()
    }

//    fun saveUserObjects(objects: List<User>) {
//
//        realm!!.executeTransaction { realm1 -> realm!!.copyToRealmOrUpdate(objects) }
//    }
//
//
//    val allUsers: RealmResults<User>
//        get() {
//            if (realm != null && realm!!.isClosed) {
//                realm = Realm.getDefaultInstance()
//            }
//            val results = realm!!.where(User::class.java).findAll()
//            return results
//        }

    fun initRealm() {
        realm = Realm.getDefaultInstance()

    }

}

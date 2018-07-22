package com.tapdevs.kotlin.data


import com.tapdevs.kotlin.models.Fruit
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

    fun saveFruitsObjects(objects: List<Fruit>) {

        realm!!.executeTransaction { realm1 -> realm!!.copyToRealmOrUpdate(objects) }
    }


    val fruits: RealmResults<Fruit>
        get() {
            if (realm != null && realm!!.isClosed) {
                realm = Realm.getDefaultInstance()
            }
            val results = realm!!.where(Fruit::class.java).findAll()
            return results
        }

    fun initRealm() {
        realm = Realm.getDefaultInstance()

    }

}

package com.tapdevs.kotlin.models

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject

open class ContainerObject() :  Parcelable{

    var fruit : List<Fruit> = ArrayList<Fruit>()

    constructor(parcel: Parcel) : this() {
        fruit = parcel.createTypedArrayList(Fruit)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(fruit)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContainerObject> {
        override fun createFromParcel(parcel: Parcel): ContainerObject {
            return ContainerObject(parcel)
        }

        override fun newArray(size: Int): Array<ContainerObject?> {
            return arrayOfNulls(size)
        }
    }

}
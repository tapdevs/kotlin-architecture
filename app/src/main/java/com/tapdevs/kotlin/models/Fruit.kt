package com.tapdevs.kotlin.models

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Fruit() : RealmObject(), Parcelable{

    @PrimaryKey var type : String = ""
    var price : Int = 0
    var weight : Int = 0

    constructor(parcel: Parcel) : this() {
        type = parcel.readString()
        price = parcel.readInt()
        weight = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeInt(price)
        parcel.writeInt(weight)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Fruit> {
        override fun createFromParcel(parcel: Parcel): Fruit {
            return Fruit(parcel)
        }

        override fun newArray(size: Int): Array<Fruit?> {
            return arrayOfNulls(size)
        }
    }


}
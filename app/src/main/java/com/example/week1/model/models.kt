package com.example.week1.model


import android.os.Parcel
import android.os.Parcelable

data class animals(var name:String? , var animalType:String? , var age:String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }
    var imageUri: String = ""

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(animalType)
        parcel.writeString(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<animals> {
        override fun createFromParcel(parcel: Parcel): animals {
            return animals(parcel)
        }

        override fun newArray(size: Int): Array<animals?> {
            return arrayOfNulls(size)
        }
    }
}
package com.example.democratics

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class info (
    val id : Int? ,
    val image: Int? ,
    val head: String?
    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()) {
    }

    companion object CREATOR : Parcelable.Creator<info> {
        override fun createFromParcel(parcel: Parcel): info {
            return info(parcel)
        }

        override fun newArray(size: Int): Array<info?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int){
        parcel.writeValue(id)
        parcel.writeValue(image)
        parcel.writeString(head)
    }
}
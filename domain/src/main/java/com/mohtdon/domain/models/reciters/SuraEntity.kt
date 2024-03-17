package com.mohtdon.domain.models.reciters

import android.os.Parcel
import android.os.Parcelable

data class SuraEntity(val originalNumber: String, val name: String, val type: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(originalNumber)
        parcel.writeString(name)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SuraEntity> {
        override fun createFromParcel(parcel: Parcel): SuraEntity {
            return SuraEntity(parcel)
        }

        override fun newArray(size: Int): Array<SuraEntity?> {
            return arrayOfNulls(size)
        }
    }
}
package com.mohtdon.domain.models.reciters

import android.os.Parcel
import android.os.Parcelable


data class MoshafEnitity(
    val reciterName: String = "",
    val id: Int = 0,
    val moshafType: Int= 0,
    val moshafName: String= "",
    val server: String= "",
    val surah_list: String= "",
    val surah_total: Int= 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(reciterName)
        parcel.writeInt(id)
        parcel.writeInt(moshafType)
        parcel.writeString(moshafName)
        parcel.writeString(server)
        parcel.writeString(surah_list)
        parcel.writeInt(surah_total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoshafEnitity> {
        override fun createFromParcel(parcel: Parcel): MoshafEnitity {
            return MoshafEnitity(parcel)
        }

        override fun newArray(size: Int): Array<MoshafEnitity?> {
            return arrayOfNulls(size)
        }
    }
}

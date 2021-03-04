package ca.ulaval.ima.tp1

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class User(val prenom: String, val nom: String, val sex: String, val program: String, val bd: String): Parcelable {
    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<User> {
            override fun createFromParcel(parcel: Parcel) = User(parcel)
            override fun newArray(size: Int) = arrayOfNulls<User>(size)
        }
    }
    constructor(parcel: Parcel) : this(
        prenom = parcel.readString() ?: "",
        nom = parcel.readString() ?: "",
        sex = parcel.readString()?: "",
        program = parcel.readString()?:"",
        //bd = parcel.readValue(Date::class.java.classLoader) as? Date
        //bd = Date(parcel.readLong())
        bd = parcel.readString()?:""
    )
    override fun describeContents(): Int {
        return 0
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(prenom)
        parcel.writeString(nom)
        //parcel.writeString(Idul)
        parcel.writeString(sex)
        parcel.writeString(bd)
//        if (bd != null) {
//            parcel.writeLong(bd.time)
//        }
        parcel.writeString(program)

    }
}

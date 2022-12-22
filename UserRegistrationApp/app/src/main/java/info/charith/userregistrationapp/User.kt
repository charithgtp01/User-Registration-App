package info.charith.userregistrationapp

import android.os.Parcel
import android.os.Parcelable

@Parcelize
data class User(
    var name: String = "",
    var email: String = "",
    var age: String = "",
    var phoneNumber: String = "",
    var profilePictureUri: String = ""
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(name)
        parcel?.writeString(email)
        parcel?.writeString(age)
        parcel?.writeString(phoneNumber)
        parcel?.writeString(profilePictureUri)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }


}

annotation class Parcelize

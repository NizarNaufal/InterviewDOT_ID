package id.interview.dotid.view.account.support

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileModels(
    @SerializedName("id")  var id_profile: Int?,
    @SerializedName("username") var username_profile: String?,
    @SerializedName("fullname")  var fullname_profile: String?,
    @SerializedName("email") var email_profile: String?,
    @SerializedName("phone")  var phone_profile: String?,
    @SerializedName("avatar") var avatar_profile:String?,
) : Parcelable
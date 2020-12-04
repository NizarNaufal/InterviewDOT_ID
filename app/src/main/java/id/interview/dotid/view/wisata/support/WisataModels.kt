package id.interview.dotid.view.wisata.support

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WisataModels(
    @SerializedName("header") var header_wisata: HeaderWisata,
    @SerializedName("content")  var content_wisata: List<ContentWisata>,
) : Parcelable


@Parcelize
data class HeaderWisata(
    @SerializedName("title")  var title_wisata: String?,
    @SerializedName("subtitle")  var subtitle_wisata: String?
) : Parcelable


@Parcelize
data class ContentWisata(
    @SerializedName("id") var header_wisata: String,
    @SerializedName("title")  var title_content: String?,
    @SerializedName("content")  var content_wisata: String?,
    @SerializedName("type")  var type_wisata: String?,
    @SerializedName("image")  var image_wisata: String?,
    @SerializedName("media")  var media_wisata: List<String>?
) : Parcelable
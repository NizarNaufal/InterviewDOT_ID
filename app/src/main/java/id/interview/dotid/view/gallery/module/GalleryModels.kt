package id.interview.dotid.view.gallery.module

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GalleryModels(
    @SerializedName("caption") var caption_gallery: String,
    @SerializedName("thumbnail")  var thumbnail_gallery: String?,
    @SerializedName("image")  var image_gallery: String?
) : Parcelable
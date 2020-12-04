package id.interview.dotid.view.gallery.support

import android.content.Context
import com.google.gson.Gson
import id.interview.dotid.repository.AppApiClient
import id.interview.dotid.repository.NetworkingState
import id.interview.dotid.repository.ViewNetworkState
import id.interview.dotid.repository.api.ResponseCode
import id.interview.dotid.view.gallery.module.GalleryModels
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class GalleryPresenter(var context: Context, var view: ViewNetworkState) : IGalleryPresenter {

    private val iteractor by lazy { GalleryIteractor(AppApiClient.mainClient()) }

    val galleryParam = "gallery.param"

    override fun getGallery() {
        view.networkState = NetworkingState.ShowLoading(Pair(galleryParam, true))
        GlobalScope.launch {
            val response = iteractor.getGallery()
            (view.networkState !is NetworkingState.Destroy).apply {
                view.networkState = NetworkingState.ShowLoading(Pair(galleryParam, false))
                when (response.first) {
                    ResponseCode.OK -> {
                        val json = JSONObject(response.second.toString())
                        val listData = Gson().fromJson(json.getJSONArray("data").toString() , Array<GalleryModels>::class.java).toList()
                        view.networkState =
                            NetworkingState.ResponseSuccess(Pair(galleryParam, listData))
                    }
                    else -> view.networkState = NetworkingState.ResponseFailure(
                        Pair(
                            galleryParam,
                            Pair(response.first, response.second)
                        )
                    )
                }
            }
        }
    }
}
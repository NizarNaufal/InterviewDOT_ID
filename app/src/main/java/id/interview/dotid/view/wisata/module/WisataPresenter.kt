package id.interview.dotid.view.wisata.module

import android.content.Context
import com.google.gson.Gson
import id.interview.dotid.repository.AppApiClient
import id.interview.dotid.repository.NetworkingState
import id.interview.dotid.repository.ViewNetworkState
import id.interview.dotid.repository.api.ResponseCode
import id.interview.dotid.view.wisata.support.WisataModels
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class WisataPresenter(var context: Context, var view: ViewNetworkState) : IWisataPresenter {

    private val iteractor by lazy { WisataIteractor(AppApiClient.mainClient()) }

    val wisataParam = "wisata.param"

    override fun getWisata() {
        view.networkState = NetworkingState.ShowLoading(Pair(wisataParam, true))
        GlobalScope.launch {
            val response = iteractor.getWisata()
            (view.networkState !is NetworkingState.Destroy).apply {
                view.networkState = NetworkingState.ShowLoading(Pair(wisataParam, false))
                when (response.first) {
                    ResponseCode.OK -> {
                        view.networkState =
                            NetworkingState.ResponseSuccess(Pair(wisataParam, response.second))
                    }
                    else -> view.networkState = NetworkingState.ResponseFailure(
                        Pair(
                            wisataParam,
                            Pair(response.first, response.second)
                        )
                    )
                }
            }
        }
    }
}
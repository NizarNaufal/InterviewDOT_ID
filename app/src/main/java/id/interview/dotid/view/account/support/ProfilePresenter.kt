package id.interview.dotid.view.account.support

import android.content.Context
import com.google.gson.Gson
import id.interview.dotid.repository.AppApiClient
import id.interview.dotid.repository.NetworkingState
import id.interview.dotid.repository.ViewNetworkState
import id.interview.dotid.repository.api.ResponseCode
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class ProfilePresenter(var context: Context, var view: ViewNetworkState) : IProfilePresenter {
    private val iteractor by lazy { ProfileIteractor(AppApiClient.mainClient()) }

    val profileParam = "profile.param"

    override fun getProfile() {
        view.networkState = NetworkingState.ShowLoading(Pair(profileParam, true))
        GlobalScope.launch {
            val response = iteractor.getProfile()
            (view.networkState !is NetworkingState.Destroy).apply {
                view.networkState = NetworkingState.ShowLoading(Pair(profileParam, false))
                when (response.first) {
                    ResponseCode.OK -> {
                        val body = JSONObject(response.second.toString()).getJSONObject("data")
                        val code = Gson().fromJson(body.toString(), ProfileModels::class.java)
                        view.networkState = NetworkingState.ResponseSuccess(Pair(profileParam, code))
                    }
                    else -> view.networkState = NetworkingState.ResponseFailure(
                        Pair(
                            profileParam,
                            Pair(response.first, response.second)
                        )
                    )
                }
            }
        }
    }
}
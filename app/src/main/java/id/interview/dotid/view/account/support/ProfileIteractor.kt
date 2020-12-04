package id.interview.dotid.view.account.support

import id.interview.dotid.repository.api.MainApi
import id.interview.dotid.repository.api.ResponseCode
import id.interview.dotid.support.ERROR_MESSAGE
import id.interview.dotid.support.FAILED_MESSAGE
import id.interview.dotid.support.showLog

class ProfileIteractor(var api: MainApi) : IProfileIteractor {
    override fun getProfile(): Pair<Int, String?> {
        return try {
            val response = api.getProfile().execute()
            when (response.isSuccessful) {
                true -> Pair(ResponseCode.OK, response.body())
                else -> {

                    val message = when (response.code()) {
                        else -> ERROR_MESSAGE
                    }

                    Pair(response.code(), message)
                }
            }

        } catch (e: Exception) {
            showLog("failed get profile : ${e.message}")
            Pair(ResponseCode.TIME_OUT, FAILED_MESSAGE)
        }
    }
}
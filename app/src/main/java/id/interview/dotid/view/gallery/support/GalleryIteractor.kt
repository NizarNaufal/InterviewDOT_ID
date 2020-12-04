package id.interview.dotid.view.gallery.support

import id.interview.dotid.repository.api.MainApi
import id.interview.dotid.repository.api.ResponseCode
import id.interview.dotid.support.ERROR_MESSAGE
import id.interview.dotid.support.FAILED_MESSAGE
import id.interview.dotid.support.showLog
import org.json.JSONObject

class GalleryIteractor(var api: MainApi) : IGalleryIteractor {

    override fun getGallery(): Pair<Int, String?> {
        return try {
            val response = api.getGalery().execute()
            when (response.isSuccessful) {
                true -> Pair(ResponseCode.OK, response.body())
                else -> {
                    val message = when (response.code()) {
                        500 -> ERROR_MESSAGE
                        else -> {
                            val json = JSONObject(response.errorBody()?.string())
                            json.getString("message")
                        }
                    }
                    Pair(response.code(), message)
                }
            }

        } catch (e: Exception) {
            showLog("failed get gallery : ${e.message}")
            Pair(ResponseCode.TIME_OUT, FAILED_MESSAGE)
        }
    }
}
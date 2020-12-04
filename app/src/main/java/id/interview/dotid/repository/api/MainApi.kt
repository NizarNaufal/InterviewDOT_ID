package id.interview.dotid.repository.api

 import retrofit2.Call
 import retrofit2.http.*

interface MainApi {

        @GET("user.json")
        fun getProfile(
        ): Call<String>

        @GET("place.json")
        fun getPlace(
        ): Call<String>

        @GET("gallery.json")
        fun getGalery(
        ): Call<String>
}
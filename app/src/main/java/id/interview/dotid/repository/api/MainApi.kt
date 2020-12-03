package id.interview.dotid.repository.api

 import retrofit2.Call
 import retrofit2.http.*

interface MainApi {

        @GET("user")
        fun getProfile(
        ): Call<String>

        @GET("place")
        fun getPlace(
        ): Call<String>

        @GET("galery")
        fun getGalery(
        ): Call<String>
}
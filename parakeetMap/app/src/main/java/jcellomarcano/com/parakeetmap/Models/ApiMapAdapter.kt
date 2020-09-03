package jcellomarcano.com.parakeetmap.Models

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMapAdapter {
    @GET("json")
    fun getPoiMaps(@Query("location", encoded = true) location: String,
                   @Query("radius") radius: String,
                   @Query("type") type: String,
                   @Query("key") API_MAPS_KEY: String
    ): Call<JsonObject>
}
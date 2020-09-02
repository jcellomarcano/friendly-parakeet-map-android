package jcellomarcano.com.parakeetmap.Models

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiMapAdapter {
    @GET("json?location={latitude},{longitude}&radius=1500&type={type}&keyword=cruise&key={API_MAPS_KEY}")
    fun getPoiMaps(@Path("latitude") latitude: String,
                   @Path("longitude") longitude: String,
                   @Path("type") type: String,
                   @Path("API_MAPS_KEY") API_MAPS_KEY: String
    ): Call<JsonObject>
}
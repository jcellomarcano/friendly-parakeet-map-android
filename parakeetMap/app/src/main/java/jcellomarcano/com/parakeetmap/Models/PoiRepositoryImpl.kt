package jcellomarcano.com.parakeetmap.Models

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import com.google.gson.JsonElement
import jcellomarcano.com.parakeetmap.Constants.GlobalConstants
import jcellomarcano.com.parakeetmap.Models.PointOfInterest
import retrofit2.Response

class PoiRepositoryImpl: PoiRepository{

    private var pois = MutableLiveData<List<PointOfInterest>>()
    //Subject MutableLiveData
    //Observers List Pois
    //Change List Pois - MutableLiveData
    //observe
    lateinit var activity: Activity


    override fun getPoiMaps(): MutableLiveData<List<PointOfInterest>> {
        return pois
    }

    //TODA LA LÓGICA DE CONEXIÓN
    override fun callPoiAPI() {
        //Controler
        var poisList: ArrayList<PointOfInterest>? = ArrayList()
        val apiMapSearchService = ApiMapSearchService()
        val apiMapAdapter = apiMapSearchService.makeRequestNearbyPoints()
        val location = UserLocation().toString()
//        location = location.format(, lat, lng)
        val call:Call<JsonObject> = apiMapAdapter.getPoiMaps(location,"15000","restaurant",GlobalConstants.API_MAPS_KEY)


        call.enqueue(object : Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR: ", t.message.toString())
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful){
                    Log.i("Repository", "onResponse:1 ${response}")
                    Log.i("Repository", "onResponse:1 message ${response.message()}")
                    if (response.body()?.has("status")!!){
                        val statusOfResponse = response.body()!!.get("status")
                        if (statusOfResponse.asString == "REQUEST_DENIED"){
                            Log.i("Repository", "onResponse: Tenemos Un Error ${response.body()!!.get("error_message")}")
                        }
                    }
                    val poiJsonActivity = response.body()?.getAsJsonArray("results")
                    poiJsonActivity?.forEach { jsonElement: JsonElement ->
                        val jsonObject = jsonElement.asJsonObject
                        val pointOfInterest = PointOfInterest(jsonObject)
                        poisList?.add(pointOfInterest)
                    }
                    Log.i("Repository", "onResponse: ${poisList?.get(0).toString()}")
                } else {
                    Log.i("Repository", "onResponse:2 ${response.message()}")
                    Log.i("Repository", "onResponse:2 Hemos fallado")

                }
                pois.value = poisList

            }


        })
    }

}






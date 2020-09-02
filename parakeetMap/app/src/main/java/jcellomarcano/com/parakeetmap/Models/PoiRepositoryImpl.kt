package jcellomarcano.com.parakeetmap.Models

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import com.google.gson.JsonElement
import jcellomarcano.com.parakeetmap.Constants.GlobalConstants
import jcellomarcano.com.parakeetmap.Models.POIs.Candidate
import retrofit2.Response

class PoiRepositoryImpl: PoiRepository{

    private var pois = MutableLiveData<List<Candidate>>()
    //Subject MutableLiveData
    //Observers List Pois
    //Change List Pois - MutableLiveData
    //observe
    lateinit var activity: Activity


    override fun getPoiMaps(): MutableLiveData<List<Candidate>> {
        return pois
    }

    //TODA LA LÓGICA DE CONEXIÓN
    override fun callPoiAPI() {
        //Controler
        var poisList: ArrayList<Candidate>? = ArrayList()
        val apiMapSearchService = ApiMapSearchService()
        val apiMapAdapter = apiMapSearchService.makeRequestNearbyPoints()
        val call = UserLocation.userLatitud?.let { UserLocation.userLongitude?.let { it1 ->
            apiMapAdapter.getPoiMaps(it,
                it1,"restaurant",GlobalConstants.API_MAPS_KEY)
        } }

        call?.enqueue(object : Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR: ", t.message.toString())
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful){
                    Log.i("Repository", "onResponse: ${response.toString()}")
                } else {
                    Log.i("Repository", "onResponse: ${response.toString()}")
                    Log.i("Repository", "onResponse:Hemos fallado")

                }
                val poiJsonActivity = response.body()?.getAsJsonArray("candidates")
                poiJsonActivity?.forEach { jsonElement: JsonElement ->
                    val jsonObject = jsonElement.asJsonObject
                    val candidate = Candidate(jsonObject)
                    poisList?.add(candidate)
                }
            }


        })
    }

}






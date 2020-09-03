package jcellomarcano.com.parakeetmap.Models

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.io.Serializable
import java.lang.Exception
import java.util.*

class PointOfInterest(poiJson: JsonObject?): Serializable{

    lateinit var geometry: JsonObject
    lateinit var icon: String
    lateinit var name: String
//    lateinit var photos: String
    lateinit var place_id: String
    var rating: Int = 0
    lateinit var reference: String
    lateinit var scope: String
    lateinit var types: JsonArray
    lateinit var vicinity: String

    init {
        try {
            place_id     = poiJson?.get(ID)?.asString ?: "00"
//            photos       = poiJson?.get(IMAGE_URL)?.asString ?: "https://dummyimage.com/300x300/c77ec7/ffffff.jpg"
            name         = poiJson?.get(NAME)?.asString ?: "Offer"
//            reference    = poiJson?.get(REFERENCE)?.asString ?: "This is an Reference"
            geometry     = poiJson?.get(LOCATION)?.asJsonObject ?: JsonObject()
            rating       = poiJson?.get(RATING)?.asInt ?: 1
            vicinity     = poiJson?.get(DIRECTION)?.asString ?: "https://www.platzi.com"
            types        = poiJson?.get(TYPE)?.asJsonArray ?: JsonArray()

        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    companion object {
        private val ID                  = "place_id"
        private val LOCATION            = "geometry"
        private val NAME                = "name"
        private val RATING              = "rating"
        private val DIRECTION           = "vicinity"
        private val TYPE                = "types"

    }




}
package jcellomarcano.com.parakeetmap.Models.POIs

import com.google.gson.JsonObject
import java.io.Serializable
import kotlin.properties.Delegates

class Candidate(candidateJson: JsonObject?): Serializable{

    lateinit var formatted_address: String
    lateinit var geometry: Geometry
    lateinit var name: String
    lateinit var opening_hours: OpeningHours
    lateinit var photos: List<Photo>
    var rating by Delegates.notNull<Double>()

//    init {
//        try {
//            id                  = candidateJson?.get(ID)?.asString ?: "00"
//            image_url           = candidateJson?.get(IMAGE_URL)?.asString ?: "https://dummyimage.com/300x300/c77ec7/ffffff.jpg"
//            name               = candidateJson?.get(TITLE)?.asString ?: "Offer"
//            description         = candidateJson?.get(DESCRIPTION)?.asString ?: "The best Offer"
//            offer               = candidateJson?.get(OFFER)?.asString ?: "It's the only chance"
//            website             = candidateJson?.get(WEBSITE)?.asString ?: "https://www.platzi.com"
//            endDate             = getFormatDate(candidateJson?.get(END_DATE)?.asString ?: Calendar.getInstance().time.toString())
//            url                 = candidateJson?.get(URL)?.asString ?: "https://www.platzi.com"
//        }catch (e: Exception){
//            e.printStackTrace()
//        }
//
//
//    }
}
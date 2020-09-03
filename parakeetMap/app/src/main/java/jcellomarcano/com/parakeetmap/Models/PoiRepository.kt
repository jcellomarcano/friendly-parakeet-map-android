package jcellomarcano.com.parakeetmap.Models

import androidx.lifecycle.MutableLiveData

interface PoiRepository {
    fun getPoiMaps(): MutableLiveData<List<PointOfInterest>>
    fun callPoiAPI()

}
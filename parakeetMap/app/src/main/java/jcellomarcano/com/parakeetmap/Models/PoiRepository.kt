package jcellomarcano.com.parakeetmap.Models

import androidx.lifecycle.MutableLiveData
import jcellomarcano.com.parakeetmap.Models.POIs.Candidate
import jcellomarcano.com.parakeetmap.Models.POIs.POI

interface PoiRepository {
    fun getPoiMaps(): MutableLiveData<List<Candidate>>
    fun callPoiAPI()

}
package jcellomarcano.com.parakeetmap.Models

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import jcellomarcano.com.parakeetmap.Models.PointOfInterest

class PointOfInterestObservable: BaseObservable() {

    private var couponRepository: PoiRepository = PoiRepositoryImpl()
    //Repositorio
    fun callPoi(){
        couponRepository.callPoiAPI()
    }

    //ViewModel
    fun getPointOfInterests() : MutableLiveData<List<PointOfInterest>> {
        return couponRepository.getPoiMaps()
    }
}
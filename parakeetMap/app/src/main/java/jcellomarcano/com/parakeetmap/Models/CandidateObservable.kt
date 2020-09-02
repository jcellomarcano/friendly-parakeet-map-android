package jcellomarcano.com.parakeetmap.Models

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import jcellomarcano.com.parakeetmap.Models.POIs.Candidate

class CandidateObservable: BaseObservable() {

    private var couponRepository: PoiRepository = PoiRepositoryImpl()
    //Repositorio
    fun callPoi(){
        couponRepository.callPoiAPI()
    }

    //ViewModel
    fun getCandidates() : MutableLiveData<List<Candidate>> {
        return couponRepository.getPoiMaps()
    }
}
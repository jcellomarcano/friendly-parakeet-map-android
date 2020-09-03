package jcellomarcano.com.parakeetmap.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jcellomarcano.com.parakeetmap.Models.PointOfInterestObservable
import jcellomarcano.com.parakeetmap.Models.PointOfInterest

class PointOfInterestViewModel: ViewModel (){
    private var pointOfInterestObservable: PointOfInterestObservable = PointOfInterestObservable()
//    private var recyclerPointOfInterestAdapter: RecyclerCouponsAdapter? = null
    var selected: MutableLiveData<PointOfInterest> = MutableLiveData<PointOfInterest>()

    fun callPoi(){
        pointOfInterestObservable.callPoi()
    }

    fun getPointOfInterests(): MutableLiveData<List<PointOfInterest>>{
        return pointOfInterestObservable.getPointOfInterests()
    }
}
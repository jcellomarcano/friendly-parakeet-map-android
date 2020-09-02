package jcellomarcano.com.parakeetmap.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jcellomarcano.com.parakeetmap.Models.CandidateObservable
import jcellomarcano.com.parakeetmap.Models.POIs.Candidate

class CandidateViewModel: ViewModel (){
    private var candidateObservable: CandidateObservable = CandidateObservable()
//    private var recyclerCandidateAdapter: RecyclerCouponsAdapter? = null
    var selected: MutableLiveData<Candidate> = MutableLiveData<Candidate>()

    fun callPoi(){
        candidateObservable.callPoi()
    }

    fun getCandidates(): MutableLiveData<List<Candidate>>{
        return candidateObservable.getCandidates()
    }
}
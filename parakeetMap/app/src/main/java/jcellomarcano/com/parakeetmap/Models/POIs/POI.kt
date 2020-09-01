package jcellomarcano.com.parakeetmap.Models.POIs

import jcellomarcano.com.parakeetmap.Models.POIs.Candidate
import jcellomarcano.com.parakeetmap.Models.POIs.DebugLog

data class POI(
    val candidates: List<Candidate>,
    val debug_log: DebugLog,
    val status: String
)
package jcellomarcano.com.parakeetmap.Models.POIs

import jcellomarcano.com.parakeetmap.Models.POIs.Northeast
import jcellomarcano.com.parakeetmap.Models.POIs.Southwest

data class Viewport(
    val northeast: Northeast,
    val southwest: Southwest
)